# kartyaszkenner
Android kártya szkenner alkalmazás valós idejű felismeréssel

KártyaSzkenner Android Alkalmazás - Teljes Implementáció
Alkalmazás leírása
Modern Android alkalmazás kártyák valós idejű felismeréséhez és gyűjtéséhez. Az alkalmazás használja a legkorszerűbb technológiákat a zökkenőmentes felhasználói élmény biztosításához.

Főbb funkciók
🎯 Valós idejű kártya felismerés
CameraX API használatával élő kamera nézet
Automatikus új kártya észlelése amikor megváltozik a látómező
Google ML Kit szöveg- és objektumfelismerés
Intelligens kártyakontúr felismerés és kivágás
🎮 Yu-Gi-Oh! kártya támogatás (első fázis)
YGOPRODeck API integráció részletes kártyaadatokhoz
Többnyelvű kártyafelismerés (angol, japán, német, stb.)
Hivatalos kártyaképek automatikus letöltése
Ritkaság, típus, támadás/védelem értékek
🎨 Modern felhasználói felület
Jetpack Compose Material Design 3
HUD (Heads-Up Display) felismert kártyákhoz
Ideiglenes lista szkennelt kártyákhoz
Sima animációk és átmenetek
Magyar nyelvű felület
🔊 Hang és vibráció
Sikeres felismerés jelzése hangefekttel
Bizonytalan felismerés eltérő hanggal
Beállítható hangerősség és típusok
Vibrációs visszajelzés opció
⚙️ Testreszabható beállítások
Felismerési sebesség vs pontosság beállítás
Kép minőség és tömörítési opciók
Duplikátum kezelés módjai
Offline/online működési preferenciák
💾 Adatkezelés
Room adatbázis a helyi adatok tárolására
Offline/online hibrid működés
CSV és JSON export funkciók
Példányszám követés minden kártyához
Technikai specifikáció
Architektúra
MVVM (Model-View-ViewModel) pattern
Clean Architecture elvek
Dependency Injection Hilt-tel
Reactive Programming Flow és StateFlow használatával
Technológiai stack
Kotlin - Programozási nyelv
Jetpack Compose - Modern UI toolkit
CameraX - Kamera kezelés
Room - Helyi adatbázis
Retrofit - Hálózati kommunikáció
Google ML Kit - Képfelismerés
Hilt - Dependency injection
Navigation Compose - Navigáció
Coil - Képbetöltés
API integráció
YGOPRODeck API (https://db.ygoprodeck.com/api/v7/cardinfo.php)
RESTful API hívások
Automatikus újrapróbálkozás hibák esetén
Rate limiting támogatás
Projekt struktúra
app/
├── src/main/
│   ├── java/com/fnagy/kartyaszkenner/
│   │   ├── KartyaSzkennerAlkalmazas.kt           // Hilt Application osztály
│   │   ├── MainActivity.kt                       // Fő Activity
│   │   │
│   │   ├── core/                                // Alapvető komponensek
│   │   │   ├── adatbazis/                      // Room adatbázis
│   │   │   │   ├── KartyaAdatbazis.kt
│   │   │   │   ├── dao/                        // Data Access Objects
│   │   │   │   │   ├── KartyaDao.kt
│   │   │   │   │   ├── KartyaTipusDao.kt
│   │   │   │   │   └── IdeiglenesKartyaDao.kt
│   │   │   │   └── konverterek/               // Type Converters
│   │   │   │       └── AdatbazisKonverterek.kt
│   │   │   │
│   │   │   ├── kamera/                        // Kamera kezelés
│   │   │   │   ├── KameraKezelo.kt
│   │   │   │   ├── KepElemzo.kt
│   │   │   │   └── KameraEngedely.kt
│   │   │   │
│   │   │   ├── kepfelismeres/                 // ML képfelismerés
│   │   │   │   ├── SzovegFelismero.kt
│   │   │   │   ├── ObjektumFelismero.kt
│   │   │   │   ├── KartyaErzekelo.kt
│   │   │   │   └── KepHashSzamito.kt
│   │   │   │
│   │   │   ├── hang/                          // Hang és vibráció
│   │   │   │   ├── HangJelzesKezelo.kt
│   │   │   │   └── VibratorKezelo.kt
│   │   │   │
│   │   │   ├── halozat/                       // Hálózati ellenőrzés
│   │   │   │   ├── HalozatFigyelo.kt
│   │   │   │   └── InternetKapcsolatElleno.kt
│   │   │   │
│   │   │   └── beallitasok/                   // Alkalmazás beállítások
│   │   │       ├── BeallitasokKezelo.kt
│   │   │       └── BeallitasKulcsok.kt
│   │   │
│   │   ├── funkciok/                          // Funkcionális modulok
│   │   │   ├── szkenneles/                    // Kártya szkennelés
│   │   │   │   ├── SzkennelesiViewModel.kt
│   │   │   │   ├── KartyaFelismeresUseCase.kt
│   │   │   │   ├── IdeiglenesListaKezelo.kt
│   │   │   │   └── UjKartyaErzekelo.kt
│   │   │   │
│   │   │   ├── gyujtemeny/                    // Gyűjtemény kezelés
│   │   │   │   ├── GyujtemenyViewModel.kt
│   │   │   │   ├── GyujtemenyRepository.kt
│   │   │   │   └── ExportSzolgaltatas.kt
│   │   │   │
│   │   │   └── beallitasok/                   // Felhasználói beállítások
│   │   │       ├── BeallitasokViewModel.kt
│   │   │       └── BeallitasokRepository.kt
│   │   │
│   │   ├── adatok/                            // Adatkezelés
│   │   │   ├── api/                           // API szolgáltatások
│   │   │   │   ├── YugiohApiService.kt
│   │   │   │   ├── KartyaApiKezelo.kt
│   │   │   │   └── dto/                       // Data Transfer Objects
│   │   │   │       ├── YugiohKartyaDto.kt
│   │   │   │       └── YugiohValaszDto.kt
│   │   │   │
│   │   │   ├── modellek/                      // Adatmodellek
│   │   │   │   ├── Kartya.kt
│   │   │   │   ├── KartyaTipus.kt
│   │   │   │   ├── IdeiglenesKartya.kt
│   │   │   │   ├── FelismertKartya.kt
│   │   │   │   └── BeallitasokAdatok.kt
│   │   │   │
│   │   │   └── repository/                    // Adattárak
│   │   │       ├── KartyaRepository.kt
│   │   │       └── BeallitasokRepository.kt
│   │   │
│   │   └── ui/                                // Felhasználói felület
│   │       ├── tema/                          // Material Design 3 témák
│   │       │   ├── Szinek.kt
│   │       │   ├── Tipografia.kt
│   │       │   ├── Forma.kt
│   │       │   └── KartyaSzkennerTema.kt
│   │       │
│   │       ├── komponensek/                   // Újrafelhasználható UI elemek
│   │       │   ├── KameraElonel.kt
│   │       │   ├── KartyaMegjelenito.kt
│   │       │   ├── ToltasJelzo.kt
│   │       │   ├── HibaUzenet.kt
│   │       │   └── BeallitasKomponensek.kt
│   │       │
│   │       ├── szkenneles/                    // Szkennelési képernyő
│   │       │   ├── SzkennelesiKepernyő.kt
│   │       │   ├── FelismertKartyaHUD.kt
│   │       │   ├── IdeiglenesListaModal.kt
│   │       │   └── KameraNezet.kt
│   │       │
│   │       ├── gyujtemeny/                    // Gyűjtemény képernyő
│   │       │   ├── GyujtemenyKepernyő.kt
│   │       │   ├── KartyaLista.kt
│   │       │   └── KartyaReszletek.kt
│   │       │
│   │       ├── beallitasok/                   // Beállítások képernyő
│   │       │   ├── BeallitasokKepernyő.kt
│   │       │   ├── BeallitasSzekcio.kt
│   │       │   └── BeallitasElemek.kt
│   │       │
│   │       └── navigacio/                     // Navigációs komponensek
│   │           ├── KartyaSzkennerNavigacio.kt
│   │           └── NavigaciosElemek.kt
│   │
│   ├── res/                                   // Erőforrások
│   │   ├── drawable/                          // Ikonok és vektoros képek
│   │   │   ├── ic_kamera.xml
│   │   │   ├── ic_beallitasok.xml
│   │   │   ├── ic_gyujtemeny.xml
│   │   │   └── ic_szkenneles.xml
│   │   │
│   │   ├── raw/                               // Hang fájlok
│   │   │   ├── sikeres_felismeres.mp3
│   │   │   ├── bizonytalan_felismeres.mp3
│   │   │   └── hiba_hang.mp3
│   │   │
│   │   ├── values/                            // Értékek és konfigurációk
│   │   │   ├── colors.xml                     // Színpaletta
│   │   │   ├── strings.xml                    // Magyar szövegek
│   │   │   ├── dimens.xml                     // Méretek
│   │   │   ├── styles.xml                     // Stílusok
│   │   │   └── themes.xml                     // Alapértelmezett témák
│   │   │
│   │   └── xml/                               // XML konfigurációk
│   │       ├── network_security_config.xml    // Hálózati biztonság
│   │       └── backup_rules.xml               // Mentési szabályok
│   │
│   └── AndroidManifest.xml                    // App manifest
│
├── build.gradle.kts                           // App szintű build konfiguráció
├── proguard-rules.pro                         // ProGuard szabályok
└── src/test/                                  // Unit tesztek alapjai
    └── java/com/fnagy/kartyaszkenner/
        └── ExampleUnitTest.kt
Implementációs prioritások
1. Alapvető konfiguráció és függőségek
Modern Android projekt beállítások (Compile SDK 34, Target SDK 34, Min SDK 24)
Všechny szükséges Gradle függőségek
Android Manifest engedélyek és konfiguráció
2. Adatbázis réteg
Room entitások és DAO-k létrehozása
Type Converter-ek komplex adattípusokhoz
Adatbázis migráció stratégia
3. Yu-Gi-Oh API integráció
Retrofit service interface
DTO modellek a Yu-Gi-Oh kártyákhoz
Hibakezelés és újrapróbálkozási logika
4. Core szolgáltatások
Kamera kezelő CameraX-szel
ML Kit képfelismerés integráció
Hang jelzés szolgáltatás
Beállítások kezelő SharedPreferences-szel
5. Felhasználói felület
Material Design 3 téma létrehozása
Fő szkennelési képernyő Compose komponensekkel
HUD komponens felismert kártyákhoz
Beállítások képernyő
6. Business logika
Repository pattern implementáció
Use Case-ek a fő funkciókhoz
ViewModel-ek reactive state management-tel
Minőségi követelmények
Kód minőség
Clean Code elvek követése
SOLID principles alkalmazása
Meaningful naming conventions
Magyar nyelvű kommentezés
Error handling minden rétegben
Teljesítmény
Optimalizált képfeldolgozás
Memória hatékony bitmap kezelés
Background threading ML műveletekhez
Lazy loading UI komponensekben
Felhasználói élmény
Gyors reagálású felület (< 16ms frame time)
Intuitive navigáció
Meaningful loading states
Graceful error handling
Tesztelési stratégia alapjai
Unit test infrastructure előkészítése
Repository mock implementációk
ViewModel tesztelési alapok
UI tesztelési framework beállítás
Ez a teljes implementáció egy professzionális szintű, production-ready Android alkalmazást fog létrehozni modern fejlesztési práktikákkal és tiszta architektúrával.
