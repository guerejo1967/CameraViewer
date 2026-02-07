# Camera Viewer - App Android per Streaming RTSP

Applicazione Android per visualizzare stream RTSP di telecamere IP distribuite in diversi siti.

## Caratteristiche

### ✨ Funzionalità Principali
- **Gestione Multi-Sito**: Organizza le telecamere per siti/location diverse
- **Streaming RTSP**: Visualizzazione in tempo reale di stream RTSP
- **Griglia Flessibile**: Layout griglia configurabile (1x1, 2x2, 3x3, 4x4)
- **Autenticazione**: Supporto per username e password RTSP
- **Import/Export**: Backup e ripristino della configurazione

### 🎯 Funzionalità di Import/Export
- **Esporta in JSON**: Salva la configurazione completa in formato JSON
- **Importa da JSON**: Ripristina la configurazione da file
  - Modalità **Sostituisci**: Elimina tutto e importa
  - Modalità **Unisci**: Aggiunge ai siti esistenti
- **Condividi come Testo**: Esporta in formato leggibile per backup manuale

## Requisiti

- Android Studio Arctic Fox o superiore
- Android SDK 24 (Android 7.0) o superiore
- JDK 8 o superiore

## Compilazione

### 1. Aprire il progetto in Android Studio
```bash
# Naviga nella cartella del progetto
cd CameraViewer

# Apri Android Studio e seleziona "Open an Existing Project"
# Seleziona la cartella CameraViewer
```

### 2. Sincronizzare le dipendenze Gradle
- Android Studio sincronizzerà automaticamente le dipendenze
- Attendi il completamento del processo di sync

### 3. Compilare l'APK

#### Opzione A: Da Android Studio (Consigliato)
1. Vai su **Build → Build Bundle(s) / APK(s) → Build APK(s)**
2. Attendi la compilazione
3. Clicca su "locate" nella notifica per trovare l'APK
4. L'APK sarà in: `app/build/outputs/apk/debug/app-debug.apk`

#### Opzione B: Da riga di comando
```bash
# Per l'APK di debug
./gradlew assembleDebug

# L'APK sarà generato in:
# app/build/outputs/apk/debug/app-debug.apk
```

#### Opzione C: APK Release (firmato)
```bash
# Crea un APK release
./gradlew assembleRelease

# L'APK sarà in:
# app/build/outputs/apk/release/app-release-unsigned.apk
```

### 4. Installare l'APK su Android

#### Via USB (ADB)
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

#### Via File Transfer
1. Copia l'APK sul dispositivo Android
2. Apri il file manager sul dispositivo
3. Tocca l'APK e conferma l'installazione
4. Potrebbe essere necessario abilitare "Origini sconosciute" nelle impostazioni

## Utilizzo dell'App

### 1. Aggiungere un Sito
- Tocca il pulsante **+** nella schermata principale
- Inserisci nome e descrizione del sito
- Tocca **Salva**

### 2. Aggiungere Telecamere
- Tocca un sito per entrare nei dettagli
- Tocca il pulsante **+** per aggiungere una telecamera
- Inserisci:
  - **Nome**: Nome identificativo della telecamera
  - **URL RTSP**: L'indirizzo di streaming (es: `rtsp://192.168.1.100:554/stream`)
  - **Username/Password**: Credenziali se richieste (opzionali)

### 3. Visualizzare le Telecamere
- Dalla schermata principale, tocca **Visualizza Telecamere** su un sito
- Lo schermo ruoterà in modalità landscape
- Usa l'icona griglia per cambiare il layout (1x1, 2x2, 3x3, 4x4)

### 4. Esportare la Configurazione
- Menu (⋮) → **Esporta Configurazione**
- Scegli dove salvare il file JSON
- Il file conterrà tutti i siti e telecamere

### 5. Importare la Configurazione
- Menu (⋮) → **Importa Configurazione**
- Seleziona un file JSON precedentemente esportato
- Scegli:
  - **Sostituisci Tutto**: Elimina dati esistenti e importa
  - **Unisci**: Aggiungi ai dati esistenti

### 6. Condividere come Testo
- Menu (⋮) → **Condividi come Testo**
- Condividi la configurazione via email, WhatsApp, ecc.

## Formato URL RTSP

### Esempi di URL RTSP validi:
```
rtsp://192.168.1.100:554/stream
rtsp://192.168.1.100:554/h264
rtsp://192.168.1.100:8554/unicast
rtsp://camera.example.com:554/live
```

### Con autenticazione nell'URL:
```
rtsp://username:password@192.168.1.100:554/stream
```

### Oppure usa i campi Username e Password nell'app
L'app inserirà automaticamente le credenziali nell'URL.

## Formato File di Configurazione

Il file JSON esportato ha questa struttura:
```json
{
  "version": 1,
  "exportDate": "2026-02-07 15:30:00",
  "sites": [
    {
      "id": 1,
      "name": "Sede Principale",
      "description": "Telecamere ufficio centrale",
      "cameras": [
        {
          "id": 1,
          "siteId": 1,
          "name": "Ingresso",
          "rtspUrl": "rtsp://192.168.1.100:554/stream",
          "username": "admin",
          "password": "password123"
        }
      ]
    }
  ]
}
```

## Risoluzione Problemi

### L'APK non si installa
- Verifica di aver abilitato "Origini sconosciute" o "Installa app sconosciute"
- Su Android 8.0+: Impostazioni → App → Menu → Accesso speciale → Installa app sconosciute

### Le telecamere non si connettono
- Verifica che l'URL RTSP sia corretto
- Controlla che il dispositivo sia sulla stessa rete delle telecamere
- Verifica username e password se richiesti
- Alcune telecamere richiedono protocolli specifici (TCP/UDP)

### Stream lento o instabile
- Riduci il numero di telecamere visualizzate contemporaneamente
- Verifica la qualità della connessione WiFi
- Le telecamere dovrebbero essere sulla stessa rete locale

### L'importazione non funziona
- Verifica che il file JSON sia valido
- Assicurati che il file sia stato esportato da questa app
- Controlla che il file non sia corrotto

## Dipendenze Principali

- **VLC for Android (libVLC)**: Per lo streaming RTSP
- **Gson**: Per serializzazione JSON
- **Material Components**: Per l'interfaccia utente
- **AndroidX**: Librerie Android moderne

## Permessi Richiesti

- **INTERNET**: Per connettersi agli stream RTSP
- **ACCESS_NETWORK_STATE**: Per verificare la connessione
- **ACCESS_WIFI_STATE**: Per ottimizzare le connessioni WiFi

## Note Tecniche

### Prestazioni
- L'app utilizza hardware decoding quando disponibile
- Supporta fino a 16 telecamere contemporanee (griglia 4x4)
- Ottimizzato per reti locali

### Sicurezza
- Le password sono salvate in locale sul dispositivo
- Usa HTTPS/TLS se le telecamere lo supportano
- Esporta la configurazione in un luogo sicuro

### Compatibilità
- Minimo: Android 7.0 (API 24)
- Target: Android 14 (API 34)
- Testato su: Android 9, 10, 11, 12, 13, 14

## Licenza

Questo progetto è fornito "as-is" per uso personale e educativo.

## Supporto

Per problemi o domande:
1. Verifica la sezione "Risoluzione Problemi"
2. Controlla che tutte le dipendenze siano installate correttamente
3. Assicurati di avere l'ultima versione di Android Studio

## Changelog

### Versione 1.0
- ✅ Gestione multi-sito
- ✅ Streaming RTSP con VLC
- ✅ Griglia configurabile
- ✅ Import/Export configurazione
- ✅ Supporto autenticazione RTSP
- ✅ Modalità landscape per visualizzazione
