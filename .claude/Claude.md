# Encryption Tool - Project Instructions

## Overview

Swing-based Java utility for encrypting and decrypting text using Spring Security Crypto (`Encryptors.delux` with AES-256-GCM).

## Tech Stack

- **Language**: Java 17
- **Build**: Apache Maven 3.9+
- **Encryption**: Spring Security Crypto 6.3.1
- **UI**: Java Swing

## Project Structure

```
src/main/java/com/example/encryptiontool/
└── EncryptionTool.java        # Main class (Swing UI + encryption logic)
pom.xml                        # Maven build config
.github/workflows/
├── test.yml                   # Claude Code action (issue/PR triggers)
└── claude-analyse.yml         # Claude analysis workflow
```

## Build & Run

```bash
mvn package                    # Build JAR
mvn exec:java -Dexec.mainClass=com.example.encryptiontool.EncryptionTool  # Run
```

## Development Conventions

- Target Java 17 language features (sealed classes, pattern matching, records where appropriate)
- Use `Encryptors.delux()` for text encryption (AES-256-GCM under the hood)
- Salt must be hex-encoded before passing to the encryptor
- Never log or print plaintext passwords or decrypted secrets
- Prefix error messages shown to users with `❌`
- Keep Swing UI code and crypto logic in separate methods for testability

## Security Guidelines

- Do not weaken encryption (e.g. no ECB mode, no DES, no hardcoded keys)
- Validate all user inputs before passing to crypto APIs
- Handle exceptions gracefully — never expose stack traces in the UI
