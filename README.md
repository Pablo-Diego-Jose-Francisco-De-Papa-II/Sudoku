# Sudoku Solver & Game in Java

🧩 O projekte
Tento projekt je implementácia hry Sudoku a solvera v Jave s využitím Swing pre grafické rozhranie. Hlavným cieľom je vytvoriť interaktívnu aplikáciu, ktorá umožňuje hráčom riešiť Sudoku, a zároveň poskytuje automatické riešenie pomocou algoritmu backtracking.

Projekt je rozdelený do viacerých tried, ktoré sa starajú o rôzne aspekty hry a solvera:

- **Tile Class:** Reprezentuje jednotlivé bunky na mriežke, vrátane validácie hodnôt a správy o tom, či je hodnota upraviteľná.
- **Grid Class:** Ukladá mriežku Sudoku ako kolekciu objektov Tile a stará sa o správne umiestnenie hodnôt.
- **Solver Class:** Implementuje algoritmus backtracking na automatické riešenie Sudoku.
- **Player Class:** Umožňuje hráčovi interagovať so hrou, vkladať čísla a poskytuje informácie o stave hry.
- **GUI Class:** Zabezpečuje grafické rozhranie, ktoré zobrazuje mriežku, ovládacie tlačidlá a funkcie ako HINT, SOLVE, NEW GAME a LEADERBOARD.

🔧 Funkcie
- **Generovanie Sudoku:** Automaticky generuje platné Sudoku úlohy, ktoré je možné riešiť.
- **Backtracking Solver:** Automatické riešenie Sudoku pomocou backtracking algoritmu.
- **Hra pre hráčov:** Interaktívna verzia Sudoku, kde hráči môžu vkladať čísla a pokúsiť sa vyriešiť úlohu.
- **Nápoveda a riešenie:** Tlačidlá pre nápovedu a automatické vyriešenie Sudoku, ak sa hráč zasekne.
- **Leaderboard:** Uloženie najlepších skóre pre motiváciu a zlepšovanie.
