# Encryption Tool

Swing-based utility for encrypting and decrypting text using Spring Security Crypto.

## Prerequisites

- Java 17 JDK (`java -version` should report 17.x)
- Apache Maven 3.9+ (`mvn -version`)

On macOS with Homebrew, install Java 17 via:

```
brew install openjdk@17
```

Ensure the JDK is on your `PATH` (e.g. `export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"`).

## Build

In the project root:

```
mvn package
```

The build produces `target/encryption-tool-0.0.1-SNAPSHOT.jar`.

## Run

Launch the Swing UI in either of two ways:

```
java -cp target/encryption-tool-0.0.1-SNAPSHOT.jar com.example.encryptiontool.EncryptionTool
```

or, via Maven (downloads plugins on first use):

```
mvn exec:java -Dexec.mainClass=com.example.encryptiontool.EncryptionTool
```

The window titled “Text Encryption/Decryption Tool” should appear.

## Usage

1. Enter a salt (any string) and password.
2. Type plain text to encrypt (or paste encrypted text to decrypt).
3. Click **Encrypt** to produce ciphertext, or **Decrypt** to recover the original text.
4. Results are shown in the lower panel; errors appear prefixed with `❌`.

For consistent results, reuse the exact same salt and password when decrypting.

