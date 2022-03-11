## ITC - inter app communication

**Modules covered in this project**
- :app (base app which stores database)
- :sdk (holds common aidl objects to be shared)
- :client (connects to `:app` to retreive data)

**APIs and Services exploited:**
- AIDL
- Synchronous / Asynchronous callback from IPC service
- Content Provider
- Handler Thread and Looper (Message Queue)
- CSV import to DB
