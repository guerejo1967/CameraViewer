# Camera Viewer - Riepilogo Completo del Progetto

## 📱 Descrizione

**Camera Viewer** è un'applicazione Android completa per la visualizzazione e gestione di stream RTSP da telecamere IP distribuite in diversi siti.

## ✨ Funzionalità Implementate

### 1. Gestione Multi-Sito
- ✅ Creazione, modifica ed eliminazione di siti
- ✅ Descrizione dettagliata per ogni sito
- ✅ Conteggio telecamere per sito
- ✅ Interfaccia intuitiva con Material Design

### 2. Gestione Telecamere
- ✅ Aggiunta telecamere per ogni sito
- ✅ Nome personalizzabile per ogni telecamera
- ✅ URL RTSP configurabile
- ✅ Supporto autenticazione (username/password)
- ✅ Validazione input URL RTSP
- ✅ Modifica ed eliminazione telecamere

### 3. Visualizzazione Stream
- ✅ Griglia configurabile (1x1, 2x2, 3x3, 4x4)
- ✅ Streaming in tempo reale con VLC
- ✅ Modalità landscape automatica
- ✅ Overlay con nome telecamera
- ✅ Indicatore di caricamento
- ✅ Gestione errori di connessione
- ✅ Hardware acceleration quando disponibile

### 4. Import/Export Configurazione ⭐ NUOVO
- ✅ **Esportazione JSON**: Salva configurazione completa
- ✅ **Importazione JSON**: Ripristina da file
  - Modalità "Sostituisci Tutto"
  - Modalità "Unisci"
- ✅ **Condivisione Testo**: Export leggibile per backup manuale
- ✅ Formato JSON ben strutturato
- ✅ Validazione import
- ✅ Preview prima dell'importazione

### 5. Persistenza Dati
- ✅ Database locale con SharedPreferences e Gson
- ✅ Salvataggio automatico
- ✅ Dati persistenti tra sessioni
- ✅ Backup tramite export

## 📂 Struttura del Progetto

```
CameraViewer/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/cameraviewer/app/
│   │       │   ├── MainActivity.kt
│   │       │   ├── SiteDetailActivity.kt
│   │       │   ├── AddEditSiteActivity.kt
│   │       │   ├── AddEditCameraActivity.kt
│   │       │   ├── CameraGridActivity.kt
│   │       │   ├── adapter/
│   │       │   │   ├── SiteAdapter.kt
│   │       │   │   ├── CameraAdapter.kt
│   │       │   │   └── CameraGridAdapter.kt
│   │       │   ├── model/
│   │       │   │   ├── Site.kt
│   │       │   │   └── Camera.kt
│   │       │   ├── database/
│   │       │   │   └── DatabaseManager.kt
│   │       │   └── utils/
│   │       │       └── ConfigManager.kt ⭐ NUOVO
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   │   ├── activity_main.xml
│   │       │   │   ├── activity_site_detail.xml
│   │       │   │   ├── activity_add_edit_site.xml
│   │       │   │   ├── activity_add_edit_camera.xml
│   │       │   │   ├── activity_camera_grid.xml
│   │       │   │   ├── item_site.xml
│   │       │   │   ├── item_camera.xml
│   │       │   │   └── item_camera_stream.xml
│   │       │   ├── menu/
│   │       │   │   └── menu_main.xml ⭐ NUOVO
│   │       │   ├── values/
│   │       │   │   ├── strings.xml
│   │       │   │   ├── colors.xml
│   │       │   │   └── themes.xml
│   │       │   └── ...
│   │       └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
├── gradle.properties
├── build-apk.sh ⭐ Script di build
├── README.md
├── GUIDA_RAPIDA.md ⭐ Guida italiana
└── esempio_configurazione.json ⭐ Esempio config

```

## 🔧 Tecnologie Utilizzate

### Linguaggio
- **Kotlin**: Linguaggio principale

### Librerie Android
- **AndroidX**: Librerie moderne Android
- **Material Components**: UI moderna e consistente
- **RecyclerView**: Liste performanti
- **CardView**: Card material design
- **ConstraintLayout**: Layout flessibili

### Librerie Esterne
- **VLC for Android (libVLC) 3.5.0**: Streaming RTSP robusto
- **Gson 2.10.1**: Serializzazione JSON
- **Room** (preparato): Database locale
- **Lifecycle Components**: Gestione ciclo vita

### Build Tools
- **Gradle 8.1.0**: Sistema di build
- **Android Gradle Plugin**: Plugin Android
- **Kotlin Plugin 1.9.0**: Supporto Kotlin

## 📋 Requisiti Tecnici

### Sviluppo
- **Android Studio**: Arctic Fox o superiore
- **JDK**: 8 o superiore
- **Android SDK**: API 24-34
- **Gradle**: 7.0+

### Dispositivo
- **Android**: 7.0 (API 24) o superiore
- **RAM**: 2GB minimo, 4GB consigliato
- **Storage**: 50MB per app
- **Network**: WiFi o dati mobili

## 🎯 Formato File Configurazione

### Struttura JSON
```json
{
  "version": 1,
  "exportDate": "2026-02-07 15:30:00",
  "sites": [
    {
      "id": 1,
      "name": "Nome Sito",
      "description": "Descrizione",
      "cameras": [
        {
          "id": 1,
          "siteId": 1,
          "name": "Nome Camera",
          "rtspUrl": "rtsp://...",
          "username": "admin",
          "password": "pass"
        }
      ]
    }
  ]
}
```

### Caratteristiche File
- ✅ Formato JSON standard
- ✅ Versioning per compatibilità futura
- ✅ Data export per riferimento
- ✅ Struttura gerarchica siti → telecamere
- ✅ Supporto credenziali opzionali
- ✅ Pretty-print per leggibilità

## 🚀 Come Compilare

### Opzione 1: Script Automatico
```bash
cd CameraViewer
./build-apk.sh
# Scegli: 1 per Debug, 2 per Release
```

### Opzione 2: Android Studio
```
1. Apri progetto in Android Studio
2. Build → Build Bundle(s) / APK(s) → Build APK(s)
3. APK in: app/build/outputs/apk/debug/
```

### Opzione 3: Gradle CLI
```bash
# Debug
./gradlew assembleDebug

# Release
./gradlew assembleRelease
```

## 📱 Installazione APK

### Da File
1. Trasferisci APK su dispositivo
2. Apri con File Manager
3. Consenti "Origini sconosciute"
4. Installa

### Via ADB
```bash
adb install app-debug.apk
```

## 💾 Gestione Dati

### Storage Locale
- **Metodo**: SharedPreferences con Gson
- **Formato**: JSON
- **Location**: `/data/data/com.cameraviewer.app/shared_prefs/`
- **Persistenza**: Permanente fino a disinstallazione

### Backup
- **Export JSON**: Backup completo esportabile
- **Import JSON**: Ripristino configurazione
- **Text Export**: Backup leggibile

## 🔐 Sicurezza e Privacy

### Gestione Password
- ⚠️ Password salvate in locale (SharedPreferences)
- ⚠️ Non criptate (considera per versioni future)
- ✅ Non condivise con server esterni
- ✅ Solo locali sul dispositivo

### Permessi
- `INTERNET`: Stream RTSP
- `ACCESS_NETWORK_STATE`: Verifica connessione
- `ACCESS_WIFI_STATE`: Ottimizzazione WiFi
- ❌ NO accesso: contatti, posizione, microfono, camera

### Raccomandazioni
1. Usa password dedicate per telecamere
2. Non condividere file export con credenziali
3. Limita accesso telecamere a rete locale
4. Cambia password periodicamente

## 🎨 Design e UX

### Principi
- **Material Design**: UI moderna e familiare
- **Consistenza**: Pattern Android standard
- **Semplicità**: Flusso utente lineare
- **Feedback**: Loading, errori, conferme

### Navigazione
```
MainActivity (Lista Siti)
  ↓ Tap sito
SiteDetailActivity (Lista Telecamere)
  ↓ Tap "Visualizza"
CameraGridActivity (Griglia Stream)
  
MainActivity
  ↓ FAB +
AddEditSiteActivity
  
SiteDetailActivity
  ↓ FAB +
AddEditCameraActivity
```

## 📊 Performance

### Ottimizzazioni
- ✅ Hardware decoding (VLC)
- ✅ Recycler views per liste
- ✅ Release risorse video
- ✅ Network caching configurato
- ✅ Buffering ottimizzato

### Limiti Testati
- **Telecamere simultanee**: Fino a 16 (4x4)
- **Siti**: Illimitati
- **Telecamere per sito**: Illimitato
- **File export**: ~1KB per telecamera

### Raccomandazioni
- **Griglia 2x2**: 4 telecamere (ideale)
- **Griglia 3x3**: 9 telecamere (buono)
- **Griglia 4x4**: 16 telecamere (richiede buona rete)

## 🐛 Known Issues e Limitazioni

### Limitazioni Attuali
1. Password non criptate (v1.0)
2. Solo protocollo RTSP (no HTTP, HLS)
3. No zoom/pan durante visualizzazione
4. No registrazione stream
5. No notifiche/allarmi

### Future Implementazioni Possibili
- [ ] Crittografia password
- [ ] Supporto HLS/HTTP
- [ ] Registrazione clip
- [ ] Motion detection
- [ ] Notifiche push
- [ ] Zoom e pan
- [ ] Audio stream
- [ ] Controllo PTZ
- [ ] Time-lapse
- [ ] Cloud sync

## 📖 Documentazione

### File Documentazione
1. **README.md**: Guida completa tecnica
2. **GUIDA_RAPIDA.md**: Quick start italiano
3. **RIEPILOGO_PROGETTO.md**: Questo file
4. **esempio_configurazione.json**: Esempio pratico

### Commenti Codice
- ✅ Ogni classe documentata
- ✅ Metodi pubblici commentati
- ✅ Logica complessa spiegata
- ✅ TODO per miglioramenti futuri

## 🎓 Come Usare

### Setup Iniziale
1. Compila APK
2. Installa su dispositivo
3. Apri app
4. Crea primo sito
5. Aggiungi telecamera di test
6. Verifica streaming

### Operazioni Comuni

#### Backup
```
Menu → Esporta Configurazione
→ Salva in Download o Drive
```

#### Ripristino
```
Menu → Importa Configurazione
→ Seleziona file JSON
→ Scegli Sostituisci/Unisci
```

#### Condivisione
```
Menu → Condividi come Testo
→ Email/WhatsApp
→ Invia backup
```

## 🔗 Collegamenti Utili

### Documentazione Riferimenti
- [VLC Android Documentation](https://wiki.videolan.org/AndroidCompile/)
- [RTSP Protocol](https://datatracker.ietf.org/doc/html/rfc2326)
- [Material Design](https://material.io/design)
- [Android Developers](https://developer.android.com/)

### Tool Utili
- **ONVIF Device Manager**: Scoperta telecamere
- **VLC Media Player**: Test URL RTSP
- **Wireshark**: Debug rete
- **ADB**: Android Debug Bridge

## 📜 Changelog

### Versione 1.0 (Attuale)
- ✅ Gestione multi-sito
- ✅ CRUD siti e telecamere
- ✅ Streaming RTSP con VLC
- ✅ Griglia configurabile (1x1 a 4x4)
- ✅ Autenticazione RTSP
- ✅ Export/Import JSON ⭐
- ✅ Condivisione testo ⭐
- ✅ Material Design UI
- ✅ Modalità landscape
- ✅ Gestione errori
- ✅ Loading indicators

## 🎉 Conclusione

L'applicazione **Camera Viewer** è completa e pronta all'uso con tutte le funzionalità richieste:

✅ Gestione siti multipli
✅ Telecamere con URL RTSP
✅ Visualizzazione griglia
✅ **Import/Export configurazione**
✅ APK compilabile

Il progetto include:
- 📱 Codice sorgente completo
- 📚 Documentazione estesa
- 🛠️ Script di build
- 📋 File di esempio
- 🇮🇹 Guide in italiano

**Pronto per compilazione e deployment!**
