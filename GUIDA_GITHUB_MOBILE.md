# 📱 COMPILARE APK CON GITHUB DAL TELEFONO

## ✅ Questa è la soluzione PIÙ FACILE e GRATUITA!

### Vantaggi:
- ✅ NON serve installare nulla sul telefono
- ✅ Compilazione in 5-10 minuti
- ✅ Completamente GRATUITO
- ✅ APK scaricabile direttamente
- ✅ Funziona su QUALSIASI telefono

---

## 🚀 GUIDA PASSO-PASSO

### Passo 1: Crea Account GitHub (5 minuti)

1. **Dal browser del telefono**, vai su: https://github.com
2. Tocca "Sign up" (in alto a destra)
3. Inserisci:
   - Email
   - Password
   - Username
4. Verifica email
5. ✅ Account creato!

---

### Passo 2: Installa App GitHub (Opzionale ma consigliato)

1. Play Store → Cerca "GitHub"
2. Installa app ufficiale GitHub
3. Login con account creato

---

### Passo 3: Carica Progetto

#### Opzione A: Da App GitHub Mobile (PIÙ FACILE)

1. Apri app GitHub
2. Tocca "+" (in basso)
3. "New repository"
4. Nome: `CameraViewer`
5. Descrizione: `App per streaming telecamere RTSP`
6. ☑️ Public
7. ☑️ Initialize with README
8. Tocca "Create repository"

9. Ora devi caricare i file:
   - Purtroppo l'app non supporta upload multipli
   - **Usa Opzione B**

#### Opzione B: Da Browser Mobile (CONSIGLIATO)

1. Browser → https://github.com
2. Login
3. Tocca "+" (in alto a destra) → "New repository"
4. Nome: `CameraViewer`
5. ☑️ Public
6. ☑️ Initialize with README
7. "Create repository"

8. **Caricare i file:**
   
   **Metodo 1: File per file (lento ma funziona)**
   - Tocca "Add file" → "Upload files"
   - Seleziona file dalla cartella CameraViewer
   - Ripeti per ogni file
   
   **Metodo 2: Desktop mode (PIÙ VELOCE)**
   - Browser → Menu → "Desktop site"
   - Trascina cartella CameraViewer
   - GitHub caricherà tutto

9. **IMPORTANTE**: Assicurati che ci sia la cartella `.github/workflows/build.yml`
   - Se non c'è, creala:
   - "Add file" → "Create new file"
   - Nome: `.github/workflows/build.yml`
   - Copia contenuto da file `build.yml` del progetto

---

### Passo 4: Compila Automaticamente

1. Vai nel tuo repository GitHub
2. Tocca tab "Actions" (in alto)
3. Se chiede di abilitare Actions → Conferma
4. Vedrai "Build Android APK" workflow
5. Se non parte automaticamente:
   - Tocca il workflow
   - "Run workflow" → "Run workflow"

6. **ATTENDI** 5-10 minuti
   - GitHub compilerà l'APK automaticamente
   - Vedrai icona 🟡 gialla = in corso
   - 🟢 verde = completato
   - 🔴 rossa = errore

---

### Passo 5: Scarica APK

1. Workflow completato (icona 🟢)
2. Tocca il workflow completato
3. Scorri in basso → "Artifacts"
4. Vedrai: `camera-viewer-debug`
5. **Tocca per scaricare** (file ZIP)

6. Estrai lo ZIP:
   - Dentro troverai `app-debug.apk`
   
7. **Installa APK**:
   - Tocca `app-debug.apk`
   - Permetti installazione da origini sconosciute
   - Installa
   - ✅ FATTO!

---

## 🎬 VIDEO TUTORIAL

Se preferisci seguire un video, cerca su YouTube:
- "How to build Android APK with GitHub Actions"
- "Compile Android app from phone GitHub"

---

## 📋 CHECKLIST

Prima di iniziare, hai:

□ Account GitHub (gratuito)
□ File CameraViewer.zip scaricato
□ Browser mobile (Chrome, Firefox, ecc.)
□ Connessione Internet WiFi (per upload)
□ 10-15 minuti di tempo

---

## 🔧 RISOLUZIONE PROBLEMI

### ❌ "Workflow failed" (icona rossa)

**Soluzione:**
1. Tocca il workflow fallito
2. Leggi il log errore
3. Cause comuni:
   - File mancanti
   - `build.yml` non nella posizione corretta
   - Sintassi errata in qualche file

**Fix rapido:**
- Cancella repository
- Ricrealo da zero
- Carica TUTTI i file del progetto
- Assicurati che la struttura sia corretta:
  ```
  CameraViewer/
  ├── .github/
  │   └── workflows/
  │       └── build.yml
  ├── app/
  ├── build.gradle
  └── settings.gradle
  ```

### ❌ "Cannot download artifact"

**Soluzione:**
- Gli artifact scadono dopo 90 giorni
- Ri-esegui il workflow
- O scarica immediatamente

### ❌ "Upload failed"

**Soluzione:**
- Connessione Internet instabile
- Prova con WiFi
- Carica file più piccoli per volta
- Usa "Desktop mode" nel browser

---

## 💡 SUGGERIMENTI

### Per aggiornamenti futuri:
1. Modifica codice dal telefono:
   - App GitHub → Edit file
   - Oppure usa app "Acode" (code editor)
2. Commit modifiche
3. Workflow si riesegue automaticamente
4. Nuovo APK pronto in 10 minuti!

### Per test veloci:
- Ogni volta che fai "commit", GitHub ricompila
- Puoi testare modifiche senza PC

### Per condividere:
- Repository pubblico = tutti possono scaricare APK
- Vai su "Releases" → Crea release → Allega APK
- Condividi link GitHub con altri

---

## 🌟 ALTERNATIVA: Gitpod (Editor Online)

Se vuoi modificare codice online:

1. Repository GitHub → URL
2. Aggiungi prima dell'URL: `gitpod.io/#`
   Esempio: `gitpod.io/#https://github.com/tuousername/CameraViewer`
3. Gitpod aprirà VS Code online
4. Modifica, salva, commit
5. GitHub Actions compilerà automaticamente

---

## 🎯 RIEPILOGO

1. ✅ Crea account GitHub (gratis)
2. ✅ Carica progetto CameraViewer
3. ✅ GitHub compila automaticamente (5-10 min)
4. ✅ Scarica APK pronto
5. ✅ Installa sul telefono

**Zero installazioni richieste sul telefono!**

---

## ❓ FAQ

**Q: È davvero gratis?**
A: Sì, 100% gratuito. GitHub Actions è gratis per repository pubblici.

**Q: Posso tenere il repository privato?**
A: Sì, ma hai limite di 2000 minuti/mese (più che sufficienti).

**Q: Funziona con qualsiasi progetto Android?**
A: Sì, qualsiasi progetto Gradle-based.

**Q: Posso compilare più volte?**
A: Sì, illimitato per repository pubblici.

**Q: Il mio codice è al sicuro?**
A: Sì, GitHub è sicuro. Se usi repo privato, solo tu vedi il codice.

**Q: Serve carta di credito?**
A: NO, nessun pagamento richiesto.

---

## 🆘 SUPPORTO

Se hai problemi:

1. **Forum GitHub Community**
   - https://github.community

2. **Stack Overflow**
   - Cerca "GitHub Actions Android build"

3. **Video Tutorial YouTube**
   - Cerca "GitHub Actions APK build tutorial"

---

## ✅ LISTA FILE NECESSARI PER GITHUB

Assicurati di caricare:

**Root directory:**
- ✅ `build.gradle`
- ✅ `settings.gradle`
- ✅ `gradle.properties`
- ✅ `.github/workflows/build.yml` ← IMPORTANTE!

**Directory app/:**
- ✅ `app/build.gradle`
- ✅ `app/src/` (intera cartella)

**Opzionali ma consigliati:**
- README.md
- .gitignore

---

Buona compilazione con GitHub! 🚀

È il metodo PIÙ SEMPLICE e AFFIDABILE per compilare dal telefono!
