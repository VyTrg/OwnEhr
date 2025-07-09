#!/bin/bash

# ================================
# Hyperledger Fabric Setup Script
# - Generates crypto materials
# - Creates Genesis block
# - Generates channel transaction
# - Generates anchor peer updates for all orgs
# ================================

set -e  # Exit immediately on error

# ==== Configuration ====
CHANNEL_NAME="ehrchannel"
SYS_CHANNEL="system-channel"

export FABRIC_CFG_PATH=$PWD
CRYPTOGEN=./bin/cryptogen
CONFIGTXGEN=./bin/configtxgen

# ==== Check required tools ====
if [ ! -f "$CRYPTOGEN" ]; then
  echo "'cryptogen' not found at $CRYPTOGEN"
  exit 1
fi

if [ ! -f "$CONFIGTXGEN" ]; then
  echo "'configtxgen' not found at $CONFIGTXGEN"
  exit 1
fi

# ==== Clean up old artifacts ====
echo "Cleaning up old network files..."
rm -rf organizations
rm -rf crypto-config
rm -rf system-genesis-block
rm -rf channel-artifacts

mkdir system-genesis-block channel-artifacts

# ==== Generate crypto materials ====
echo "Generating crypto materials..."
$CRYPTOGEN generate --config=./crypto-config.yaml --output=./organizations

# ==== Generate Genesis block ====
echo "Creating genesis block for orderer..."
$CONFIGTXGEN -profile GenesisProfile \
  -channelID $SYS_CHANNEL \
  -outputBlock ./system-genesis-block/genesis.block

# ==== Generate channel creation transaction ====
echo "Creating channel transaction file for '$CHANNEL_NAME'..."
$CONFIGTXGEN -profile EHRChannel \
  -outputCreateChannelTx ./channel-artifacts/${CHANNEL_NAME}.tx \
  -channelID $CHANNEL_NAME

# ==== Generate anchor peer updates ====
echo "Generating anchor peer updates..."

$CONFIGTXGEN -profile EHRChannel \
  -outputAnchorPeersUpdate ./channel-artifacts/OrgUserMSPanchors.tx \
  -channelID $CHANNEL_NAME -asOrg OrgUserMSP

$CONFIGTXGEN -profile EHRChannel \
  -outputAnchorPeersUpdate ./channel-artifacts/OrgViewerMSPanchors.tx \
  -channelID $CHANNEL_NAME -asOrg OrgViewerMSP

$CONFIGTXGEN -profile EHRChannel \
  -outputAnchorPeersUpdate ./channel-artifacts/OrgAdminMSPanchors.tx \
  -channelID $CHANNEL_NAME -asOrg OrgAdminMSP

echo "Network configuration generated successfully for channel '$CHANNEL_NAME'"
