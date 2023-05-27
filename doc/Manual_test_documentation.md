# Manuális tesztek dokumentációja

A házi feladat során a Szoftver projekt laboratórium tárgyra készített alkalmazás ellenőrzésével foglalkoztunk. Az egyik kiválasztott feladat a manuális tesztek készítése, kipróbálása és tesztek eredményeinek analizálása volt.

A manuális tesztek készítése során igyekeztem az alkalmazás dokumentációjában található követelmények közül a legfontosabbakat lefedni teszttel. Próbáltam megfelelően komplex teszteket készíteni annak érdekében, hogy a tesztek egyértelműen felbonthatóak legyenek egységnyi lépésekre, amiket az adott lépés után közvetlenül ki lehet értékelni az elvárt és a tapasztalt viselkedés alapján. Arra is próbáltam figyelni, hogy ne legyen túl sok teszt, amiben hasonló követelmények teljesülését vizsgáljuk csak éppen egy másik ágenssel. Ezért úgy csoportosítottam a teszteket,  hogy egy teszten belül lehessen kipróbálni a működést a különböző esetekre és ne kelljen az előkészületeket minden próba előtt ismét megtenni.

Hasznosnak bizonyultak a tesztek, többen is kipróbáltuk őket és hasonló eredményekre jutottunk. Több lépésnél nem az lett a viselkedés, mint amit eredetileg elvártunk volna a követelmények alapján és ezek között voltak igen súlyos eltérések is, amik a felhasználó élményt nagyon elrontják.
A legsúlyosabb hibáknak a következőket találtuk:
1. egy alapvető követelmény, hogy ha valamelyik játékos megtanulja az össze genetikus kódot, akkor ő nyert és a játéknak vége, ez azonban nem teljesül be
2. amikor szeretnénk egy ágenst létrehozni, mindig csak azt az ágenst tudjuk létrehozni, aminek legelőször tanultuk meg a kódját
3. amikor egy felszerelést felveszünk, az több példányban kerül be a készletünkbe és előfodul, hogy a hatása is többszöröse lesz az elvártnak

Az eredmények igazi hasznossága szerintem abban rejlik, hogy ezek a hibák egység tesztek, ui tesztek vagy integrációs tesztek futtatásával nem feltétlenül jelentkeztek volna. Ezért még mindig nagy jelentősége van a manuális teszteknek, bármennyire monoton és favágó megoldás. 

A megtervezett tesztekről lépésekre bontva és az eredményeikről az ebben a mappában található Manual_tests.txt fájlban vannak bővebb információk.