A private blockchain network, where manages key of user, to ensure user get correct key to decrypt data in off-chain data(EHRBase) and another privacy policies for better security of applications

# Key of function(Plan)
- Audit (IHE ATNA)
- Keymanagement
- Authen by rule
> All TLS communitie

# Enviroments
- Ubuntu 25.04
- Docker Engine Version 28.3.1
- Fabric binary 2.5.12
- Docker Compose Version v2.38.1-desktop.1

# Network Structure
## OrgUser
General users
## OrgViewer
Data consumers
> About User and Viewer, pending to attemp if split to anothers peer or same peer
## OrgAdmin
The roles of administrative users (system administrators) and super administrators have not been addressed in the current architecture. These roles are planned to be assessed and potentially integrated in future versions of the system.
## OrdererOrg
Manages the ordering service node

# Channel: ehrchannel
> Still address about number of channels

# Result
- Created ehrchannel successfully.

# Future plan
- Chaincode intergretation(smart contact)
- Key management with off-chain database: EHRbase
- Client connect with Java in /backend
- Combine with RBAC