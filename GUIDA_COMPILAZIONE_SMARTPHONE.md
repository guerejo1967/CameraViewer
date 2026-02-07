# 📱 GUIDA: Compilare APK su Smartphone

## ✅ METODO 1: AIDE (PIÙ SEMPLICE)

### Passo 1: Installazione
1. Apri Play Store
2. Cerca "AIDE - Android IDE"
3. Installa l'app (circa 50MB)

### Passo 2: Preparazione Progetto
1. Scarica `CameraViewer.zip` sul telefono
2. Installa "ZArchiver" dal Play Store se non hai un estrattore
3. Apri ZArchiver
4. Trova `CameraViewer.zip` (solitamente in Download)
5. Tocca il file → Estrai qui

### Passo 3: Aprire in AIDE
1. Apri AIDE
2. Consenti permessi storage
3. Tocca icona cartella (in alto a sinistra)
4. Vai su "Device Storage" o "Download"
5. Trova e tocca la cartella `CameraViewer`

### Passo 4: Compilazione
1. AIDE aprirà il progetto
2. Menu (tre puntini) → "More" → "Build & Run"
3. **ATTENDI**: può richiedere 15-30 minuti!
4. Se chiede di installare dipendenze → Accetta

### Passo 5: Trovare APK
1. Dopo la compilazione, vai in:
   `CameraViewer/app/build/outputs/apk/debug/`
2. Troverai `app-debug.apk`
3. Tocca per installare

### ⚠️ Problemi Comuni AIDE:

**"Out of memory"**
- Chiudi tutte le altre app
- Riavvia il telefono
- Riprova

**"Gradle sync failed"**
- AIDE gratuita potrebbe avere limitazioni
- Prova versione premium (a pagamento)

**"Build failed"**
- Verifica di aver estratto correttamente lo ZIP
- Assicurati di aver aperto la cartella principale `CameraViewer`

---

## ✅ METODO 2: TERMUX (Per Esperti)

### Requisiti
- Telefono con almeno 4GB RAM
- 2GB spazio libero
- Pazienza (richiede 30-60 minuti)

### Installazione Termux
1. NON usare Play Store (versione vecchia)
2. Vai su https://f-droid.org/
3. Scarica app F-Droid
4. Installa F-Droid
5. Cerca "Termux" in F-Droid
6. Installa Termux

### Setup
Apri Termux e incolla questi comandi uno alla volta:

```bash
# Aggiorna Termux
pkg update && pkg upgrade

# Installa Java e Gradle (ATTENDI: 10-15 minuti)
pkg install openjdk-17 gradle wget

# Permessi storage
termux-setup-storage
```

### Preparazione Progetto
```bash
# Vai nella home
cd ~

# Copia progetto da Download (modifica percorso se necessario)
cp -r /sdcard/Download/CameraViewer ~/

# Entra nel progetto
cd CameraViewer

# Rendi gradlew eseguibile
chmod +x gradlew
```

### Compilazione
```bash
# QUESTO RICHIEDERÀ 30-60 MINUTI!
./gradlew assembleDebug --no-daemon
```

### Recupera APK
```bash
# Copia APK in Download
cp app/build/outputs/apk/debug/app-debug.apk /sdcard/Download/camera-viewer.apk

# Fatto!
echo "✅ APK copiato in /sdcard/Download/camera-viewer.apk"
```

### Installazione
1. Apri File Manager
2. Vai su Download
3. Tocca `camera-viewer.apk`
4. Installa

---

## ✅ METODO 3: ONLINE (SENZA INSTALLARE NULLA)

### Usando GitHub Actions (GRATUITO)

1. **Crea account GitHub** (se non ce l'hai)
   - Vai su github.com
   - Registrati gratis

2. **Carica progetto**
   - Crea nuovo repository
   - Carica file ZIP o cartella estratta
   - Rendi repository pubblico

3. **Crea workflow**
   Crea file `.github/workflows/build.yml` con:
   ```yaml
   name: Build APK
   on: [push]
   jobs:
     build:
       runs-on: ubuntu-latest
       steps:
         - uses: actions/checkout@v3
         - uses: actions/setup-java@v3
           with:
             java-version: '17'
             distribution: 'temurin'
         - name: Build APK
           run: |
             chmod +x gradlew
             ./gradlew assembleDebug
         - name: Upload APK
           uses: actions/upload-artifact@v3
           with:
             name: app-debug
             path: app/build/outputs/apk/debug/app-debug.apk
   ```

4. **Scarica APK**
   - Vai su "Actions" nel repository
   - Aspetta compilazione (5-10 min)
   - Scarica APK compilato

---

## 🎯 QUALE SCEGLIERE?

### Se hai:
- **1-2GB RAM**: Metodo 3 (Online)
- **3-4GB RAM**: Metodo 1 (AIDE)
- **6GB+ RAM e competenze**: Metodo 2 (Termux)

### Il più SEMPLICE:
**Metodo 1 (AIDE)** - basta installare un'app

### Il più AFFIDABILE:
**Metodo 3 (Online)** - non richiede risorse telefono

---

## 💡 ALTERNATIVA: USA UN PC

Se hai accesso a qualsiasi PC (anche non tuo):

1. Installa Android Studio Portable (non richiede admin)
2. Compila in 10 minuti
3. Trasferisci APK al telefono

**Link Android Studio:**
https://developer.android.com/studio

---

## ❓ FAQ

**Q: Quanto tempo ci vuole?**
A: 
- AIDE: 15-30 minuti
- Termux: 30-60 minuti
- Online: 5-10 minuti (ma devi configurare)

**Q: Quanta memoria serve?**
A: Minimo 2GB liberi

**Q: È sicuro?**
A: Sì, stai compilando il TUO codice sorgente

**Q: L'APK funzionerà?**
A: Sì, identico a quello compilato da PC

**Q: Serve root?**
A: NO, non serve root

---

## 🆘 SUPPORTO

Se hai problemi:

1. **Errore "Out of Memory"**
   → Chiudi tutte le app e riavvia

2. **"Build Failed"**
   → Controlla log errori
   → Verifica spazio disponibile

3. **APK non si installa**
   → Abilita "Origini sconosciute"
   → Settings → Security → Unknown Sources

4. **Troppo lento**
   → Usa metodo online (GitHub Actions)

---

## ✅ CHECKLIST PRE-COMPILAZIONE

Prima di iniziare, verifica:

□ Spazio libero: almeno 2GB
□ RAM disponibile: almeno 2GB
□ Batteria: >50% o in carica
□ Connessione: WiFi (per download dipendenze)
□ Pazienza: potrebbero servire 30+ minuti

---

Buona compilazione! 🚀
