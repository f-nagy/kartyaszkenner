# kartyaszkenner
Android kártya szkenner alkalmazás valós idejű felismeréssel

KártyaSzkenner Android Alkalmazás - Teljes Implementáció<br>
Alkalmazás leírása<br>
Modern Android alkalmazás kártyák valós idejű felismeréséhez és gyűjtéséhez. Az alkalmazás használja a legkorszerűbb technológiákat a zökkenőmentes felhasználói élmény biztosításához.<br>

Főbb funkciók:<br>
🎯 Valós idejű kártya felismerés<br>
CameraX API használatával élő kamera nézet<br>
Automatikus új kártya észlelése amikor megváltozik a látómező<br>
Google ML Kit szöveg- és objektumfelismerés<br>
Intelligens kártyakontúr felismerés és kivágás<br>

🎮 Yu-Gi-Oh! kártya támogatás (első fázis)<br>
YGOPRODeck API integráció részletes kártyaadatokhoz<br>
Többnyelvű kártyafelismerés (angol, japán, német, stb.)<br>
Hivatalos kártyaképek automatikus letöltése<br>
Ritkaság, típus, támadás/védelem értékek<br>

🎨 Modern felhasználói felület<br>
Jetpack Compose Material Design 3<br>
HUD (Heads-Up Display) felismert kártyákhoz<br>
Ideiglenes lista szkennelt kártyákhoz<br>
Sima animációk és átmenetek<br>
Magyar nyelvű felület<br>

🔊 Hang és vibráció<br>
Sikeres felismerés jelzése hangefekttel<br>
Bizonytalan felismerés eltérő hanggal<br>
Beállítható hangerősség és típusok<br>
Vibrációs visszajelzés opció<br>

⚙️ Testreszabható beállítások<br>
Felismerési sebesség vs pontosság beállítás<br>
Kép minőség és tömörítési opciók<br>
Duplikátum kezelés módjai<br>
Offline/online működési preferenciák<br>

💾 Adatkezelés<br>
Room adatbázis a helyi adatok tárolására<br>
Offline/online hibrid működés<br>
CSV és JSON export funkciók<br>
Példányszám követés minden kártyához<br>

Technikai specifikáció<br>
Architektúra<br>
MVVM (Model-View-ViewModel) pattern<br>
Clean Architecture elvek<br>
Dependency Injection Hilt-tel<br>
Reactive Programming Flow és StateFlow használatával<br>
Technológiai stack<br>
Kotlin - Programozási nyelv<br>
Jetpack Compose - Modern UI toolkit<br>
CameraX - Kamera kezelés<br>
Room - Helyi adatbázis<br>
Retrofit - Hálózati kommunikáció<br>
Google ML Kit - Képfelismerés<br>
Hilt - Dependency injection<br>
Navigation Compose - Navigáció<br>
Coil - Képbetöltés<br>
API integráció<br>
YGOPRODeck API (https://db.ygoprodeck.com/api/v7/cardinfo.php)<br>
RESTful API hívások<br>
Automatikus újrapróbálkozás hibák esetén<br>
Rate limiting támogatás<br>

Projekt struktúra<br>
app/<br>
├── src/main/<br>
│   ├── java/com/fnagy/kartyaszkenner/<br>
│   │   ├── KartyaSzkennerAlkalmazas.kt           // Hilt Application osztály<br>
│   │   ├── MainActivity.kt                       // Fő Activity<br>
│   │   │<br>
│   │   ├── core/                                // Alapvető komponensek<br>
│   │   │   ├── adatbazis/                      // Room adatbázis<br>
│   │   │   │   ├── KartyaAdatbazis.kt<br>
│   │   │   │   ├── dao/                        // Data Access Objects<br>
│   │   │   │   │   ├── KartyaDao.kt<br>
│   │   │   │   │   ├── KartyaTipusDao.kt<br>
│   │   │   │   │   └── IdeiglenesKartyaDao.kt<br>
│   │   │   │   └── konverterek/               // Type Converters<br>
│   │   │   │       └── AdatbazisKonverterek.kt<br>
│   │   │   │<br>
│   │   │   ├── kamera/                        // Kamera kezelés<br>
│   │   │   │   ├── KameraKezelo.kt<br>
│   │   │   │   ├── KepElemzo.kt<br>
│   │   │   │   └── KameraEngedely.kt<br>
│   │   │   │<br>
│   │   │   ├── kepfelismeres/                 // ML képfelismerés<br>
│   │   │   │   ├── SzovegFelismero.kt<br>
│   │   │   │   ├── ObjektumFelismero.kt<br>
│   │   │   │   ├── KartyaErzekelo.kt<br>
│   │   │   │   └── KepHashSzamito.kt<br>
│   │   │   │<br>
│   │   │   ├── hang/                          // Hang és vibráció<br>
│   │   │   │   ├── HangJelzesKezelo.kt<br>
│   │   │   │   └── VibratorKezelo.kt<br>
│   │   │   │<br>
│   │   │   ├── halozat/                       // Hálózati ellenőrzés<br>
│   │   │   │   ├── HalozatFigyelo.kt<br>
│   │   │   │   └── InternetKapcsolatElleno.kt<br>
│   │   │   │<br>
│   │   │   └── beallitasok/                   // Alkalmazás beállítások<br>
│   │   │       ├── BeallitasokKezelo.kt<br>
│   │   │       └── BeallitasKulcsok.kt<br>
│   │   │<br>
│   │   ├── funkciok/                          // Funkcionális modulok<br>
│   │   │   ├── szkenneles/                    // Kártya szkennelés<br>
│   │   │   │   ├── SzkennelesiViewModel.kt<br>
│   │   │   │   ├── KartyaFelismeresUseCase.kt<br>
│   │   │   │   ├── IdeiglenesListaKezelo.kt<br>
│   │   │   │   └── UjKartyaErzekelo.kt<br>
│   │   │   │<br>
│   │   │   ├── gyujtemeny/                    // Gyűjtemény kezelés<br>
│   │   │   │   ├── GyujtemenyViewModel.kt<br>
│   │   │   │   ├── GyujtemenyRepository.kt<br>
│   │   │   │   └── ExportSzolgaltatas.kt<br>
│   │   │   │<br>
│   │   │   └── beallitasok/                   // Felhasználói beállítások<br>
│   │   │       ├── BeallitasokViewModel.kt<br>
│   │   │       └── BeallitasokRepository.kt<br>
│   │   │<br>
│   │   ├── adatok/                            // Adatkezelés<br>
│   │   │   ├── api/                           // API szolgáltatások<br>
│   │   │   │   ├── YugiohApiService.kt<br>
│   │   │   │   ├── KartyaApiKezelo.kt<br>
│   │   │   │   └── dto/                       // Data Transfer Objects<br>
│   │   │   │       ├── YugiohKartyaDto.kt<br>
│   │   │   │       └── YugiohValaszDto.kt<br>
│   │   │   │<br>
│   │   │   ├── modellek/                      // Adatmodellek<br>
│   │   │   │   ├── Kartya.kt<br>
│   │   │   │   ├── KartyaTipus.kt<br>
│   │   │   │   ├── IdeiglenesKartya.kt<br>
│   │   │   │   ├── FelismertKartya.kt<br>
│   │   │   │   └── BeallitasokAdatok.kt<br>
│   │   │   │<br>
│   │   │   └── repository/                    // Adattárak<br>
│   │   │       ├── KartyaRepository.kt<br>
│   │   │       └── BeallitasokRepository.kt<br>
│   │   │<br>
│   │   └── ui/                                // Felhasználói felület<br>
│   │       ├── tema/                          // Material Design 3 témák<br>
│   │       │   ├── Szinek.kt<br>
│   │       │   ├── Tipografia.kt<br>
│   │       │   ├── Forma.kt<br>
│   │       │   └── KartyaSzkennerTema.kt<br>
│   │       │<br>
│   │       ├── komponensek/                   // Újrafelhasználható UI elemek<br>
│   │       │   ├── KameraElonel.kt<br>
│   │       │   ├── KartyaMegjelenito.kt<br>
│   │       │   ├── ToltasJelzo.kt<br>
│   │       │   ├── HibaUzenet.kt<br>
│   │       │   └── BeallitasKomponensek.kt<br>
│   │       │<br>
│   │       ├── szkenneles/                    // Szkennelési képernyő<br>
│   │       │   ├── SzkennelesiKepernyő.kt<br>
│   │       │   ├── FelismertKartyaHUD.kt<br>
│   │       │   ├── IdeiglenesListaModal.kt<br>
│   │       │   └── KameraNezet.kt<br>
│   │       │<br>
│   │       ├── gyujtemeny/                    // Gyűjtemény képernyő<br>
│   │       │   ├── GyujtemenyKepernyő.kt<br>
│   │       │   ├── KartyaLista.kt<br>
│   │       │   └── KartyaReszletek.kt<br>
│   │       │<br>
│   │       ├── beallitasok/                   // Beállítások képernyő<br>
│   │       │   ├── BeallitasokKepernyő.kt<br>
│   │       │   ├── BeallitasSzekcio.kt<br>
│   │       │   └── BeallitasElemek.kt<br>
│   │       │<br>
│   │       └── navigacio/                     // Navigációs komponensek<br>
│   │           ├── KartyaSzkennerNavigacio.kt<br>
│   │           └── NavigaciosElemek.kt<br>
│   │<br>
│   ├── res/                                   // Erőforrások<br>
│   │   ├── drawable/                          // Ikonok és vektoros képek<br>
│   │   │   ├── ic_kamera.xml<br>
│   │   │   ├── ic_beallitasok.xml<br>
│   │   │   ├── ic_gyujtemeny.xml<br>
│   │   │   └── ic_szkenneles.xml<br>
│   │   │<br>
│   │   ├── raw/                               // Hang fájlok<br>
│   │   │   ├── sikeres_felismeres.mp3<br>
│   │   │   ├── bizonytalan_felismeres.mp3<br>
│   │   │   └── hiba_hang.mp3<br>
│   │   │<br>
│   │   ├── values/                            // Értékek és konfigurációk<br>
│   │   │   ├── colors.xml                     // Színpaletta<br>
│   │   │   ├── strings.xml                    // Magyar szövegek<br>
│   │   │   ├── dimens.xml                     // Méretek<br>
│   │   │   ├── styles.xml                     // Stílusok<br>
│   │   │   └── themes.xml                     // Alapértelmezett témák<br>
│   │   │<br>
│   │   └── xml/                               // XML konfigurációk<br>
│   │       ├── network_security_config.xml    // Hálózati biztonság<br>
│   │       └── backup_rules.xml               // Mentési szabályok<br>
│   │<br>
│   └── AndroidManifest.xml                    // App manifest<br>
│<br>
├── build.gradle.kts                           // App szintű build konfiguráció<br>
├── proguard-rules.pro                         // ProGuard szabályok<br>
└── src/test/                                  // Unit tesztek alapjai<br>
    └── java/com/fnagy/kartyaszkenner/<br>
        └── ExampleUnitTest.kt<br>

Implementációs prioritások<br>
1. Alapvető konfiguráció és függőségek<br>
•	Modern Android projekt beállítások (Compile SDK 34, Target SDK 34, Min SDK 24)<br>
•	Všechny szükséges Gradle függőségek<br>
•	Android Manifest engedélyek és konfiguráció<br>

3. Adatbázis réteg<br>
•	Room entitások és DAO-k létrehozása<br>
•	Type Converter-ek komplex adattípusokhoz<br>
•	Adatbázis migráció stratégia<br>

5. Yu-Gi-Oh API integráció<br>
•	Retrofit service interface<br>
•	DTO modellek a Yu-Gi-Oh kártyákhoz<br>
•	Hibakezelés és újrapróbálkozási logika<br>

7. Core szolgáltatások<br>
•	Kamera kezelő CameraX-szel<br>
•	ML Kit képfelismerés integráció<br>
•	Hang jelzés szolgáltatás<br>
•	Beállítások kezelő SharedPreferences-szel<br>

9. Felhasználói felület<br>
•	Material Design 3 téma létrehozása<br>
•	Fő szkennelési képernyő Compose komponensekkel<br>
•	HUD komponens felismert kártyákhoz<br>
•	Beállítások képernyő<br>

11. Business logika<br>
•	Repository pattern implementáció<br>
•	Use Case-ek a fő funkciókhoz<br>
•	ViewModel-ek reactive state management-tel<br>

Minőségi követelmények<br>
Kód minőség<br>
•	Clean Code elvek követése<br>
•	SOLID principles alkalmazása<br>
•	Meaningful naming conventions<br>
•	Magyar nyelvű kommentezés<br>
•	Error handling minden rétegben<br>

Teljesítmény<br>
•	Optimalizált képfeldolgozás<br>
•	Memória hatékony bitmap kezelés<br>
•	Background threading ML műveletekhez<br>
•	Lazy loading UI komponensekben<br>

Felhasználói élmény<br>
•	Gyors reagálású felület (< 16ms frame time)<br>
•	Intuitive navigáció<br>
•	Meaningful loading states<br>
•	Graceful error handling<br>

Tesztelési stratégia alapjai<br>
•	Unit test infrastructure előkészítése<br>
•	Repository mock implementációk<br>
•	ViewModel tesztelési alapok<br>
•	UI tesztelési framework beállítás<br>

Ez a teljes implementáció egy professzionális szintű, production-ready Android alkalmazást fog létrehozni modern fejlesztési práktikákkal és tiszta architektúrával.<br>
