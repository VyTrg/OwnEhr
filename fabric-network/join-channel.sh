#!/bin/bash

# Exit on any error
set -e

CHANNEL_NAME="ehrchannel"
ORDERER_ADDRESS="orderer.example.com:7050"
EHR_BLOCK=/etc/hyperledger/fabric/genesis.block
CAFILE=/etc/hyperledger/fabric/tls/ca.crt

# ================================
# Function to join a peer to channel
# ================================
join_channel() {
  PEER_NAME=$1
  ORG_MSP=$2
  MSP_PATH=$3
  PEER_ADDRESS=$4

  echo "==> Joining $PEER_NAME to channel..."

  docker exec \
    -e CORE_PEER_LOCALMSPID=$ORG_MSP \
    -e CORE_PEER_MSPCONFIGPATH=$MSP_PATH \
    -e CORE_PEER_ADDRESS=$PEER_ADDRESS \
    $PEER_NAME \
    peer channel join -b $EHR_BLOCK
}

# ================================
# Function to update anchor peer
# ================================
update_anchor() {
  PEER_NAME=$1
  ORG_MSP=$2
  MSP_PATH=$3
  ANCHOR_TX=$4

  echo "==> Updating anchor peer for $ORG_MSP..."

  docker exec \
    -e CORE_PEER_LOCALMSPID=$ORG_MSP \
    -e CORE_PEER_MSPCONFIGPATH=$MSP_PATH \
    $PEER_NAME \
    peer channel update \
      -o $ORDERER_ADDRESS \
      --ordererTLSHostnameOverride orderer.example.com \
      -c $CHANNEL_NAME \
      -f $ANCHOR_TX \
      --tls --cafile $CAFILE
}

# ================================
# Join all peers to the channel
# ================================

# OrgUser
join_channel peer0.orguser.example.com OrgUserMSP /etc/hyperledger/fabric/msp peer0.orguser.example.com:7051
update_anchor peer0.orguser.example.com OrgUserMSP /etc/hyperledger/fabric/msp/users/Admin@orguser.example.com/msp /etc/hyperledger/fabric/channel-artifacts/OrgUserMSPanchors.tx

# OrgViewer
join_channel peer0.orgviewer.example.com OrgViewerMSP /etc/hyperledger/fabric/msp/users/Admin@orgviewer.example.com/msp peer0.orgviewer.example.com:9051
update_anchor peer0.orgviewer.example.com OrgViewerMSP /etc/hyperledger/fabric/msp/users/Admin@orgviewer.example.com/msp /etc/hyperledger/fabric/channel-artifacts/OrgViewerMSPanchors.tx

# OrgAdmin
join_channel peer0.orgadmin.example.com OrgAdminMSP /etc/hyperledger/fabric/msp/users/Admin@orgadmin.example.com/msp peer0.orgadmin.example.com:11051
update_anchor peer0.orgadmin.example.com OrgAdminMSP /etc/hyperledger/fabric/msp/users/Admin@orgadmin.example.com/msp /etc/hyperledger/fabric/channel-artifacts/OrgAdminMSPanchors.tx

echo "All peers have joined channel '$CHANNEL_NAME' and anchor peers are updated."
