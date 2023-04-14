#include"klasy.h"
#include<filesystem>
Osoba::Osoba(string i, string n, string m, string h,string p)
	:imie(i), nazwisko(n), mail(m), haslo(h),pesel(p) {};

Osoba::Osoba() :imie(""), nazwisko(""), mail(""), haslo(""),pesel("") {};




Konto::Konto() :Osoba(), nr_konta(0), saldo(0) {};
Konto::Konto(int nr, double s, string i, string n, string m, string h,string p)
	:Osoba(i, n, m, h,p), nr_konta(nr), saldo(s) {};


Menu::Menu() {};
void Menu::GetBaza()
{
	filesystem::path pth = std::filesystem::current_path();
	//cout << pth << endl;
	pth /= ("test.txt");

	string tekst;
	ifstream file(pth);
	getline(file, tekst);
	file.close();
	cout << tekst;

}