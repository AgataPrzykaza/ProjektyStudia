#pragma once
#include <iostream>
#include<string>
#include<vector>
#include<filesystem>
#include<fstream>
using namespace std;



class Osoba
{
protected:
	string imie;
	string nazwisko;
	string mail;
	string haslo;
	string pesel;
	

public:
	
	Osoba();
	Osoba(string, string, string, string,string) ;

	~Osoba() {};
};

class Transakcja
{
private:

	string data;
	double kwota;
	string	tytul;
	string	typ;
public:
	Transakcja() {};
	Transakcja(string, double, string);
	void Dodaj();
	void Odejmij();
	void Show();

	~Transakcja() {};
};

class Przelew:public Transakcja
{
private:
	int nr_odbiorcy;
public:
	Przelew();
	~Przelew(){};
};

class Kredyt:public Transakcja
{
private:
	string okres;
	string rata;
	string procent;
public:
	Kredyt(){};
	void WeryfikacjaZdolnosci(){};
	void Dodaj(){};
	void Odejmij(){};
	void Show(){};
	~Kredyt(){};
};

class Wyplata :public Transakcja
{
private:

public:
	Wyplata();
	void Dodaj();
	void Odejmij();
	void Show();
	~Wyplata();
};
class Konto:private Osoba
{
private:
	int nr_konta;
	double saldo;

public:
	Konto();
	Konto(int, double, string, string, string, string,string);
	void GetImieNazwisko();
	void SetImieNazwisko();
	void GetMail();
	void SetMail();
	void GetHaslo();
	void SetHaslo();
	void ChangeSaldo(double);
	void DodajKonto(vector<pair<Konto,vector<Transakcja*>>>);
	void UsunKonto(vector<pair<Konto, vector<Transakcja*>>>);
	void Modyfikacja(vector<pair<Konto, vector<Transakcja*>>>, Konto);
	~Konto() {};
	
};
class Menu
{
	
private:
	vector < pair<Konto, vector<Transakcja*>>> baza;
	bool status;
	bool uprawnienia;
	
	
public:
	Menu();
	void GetBaza();
	void SaveBaza(){};
	~Menu(){};
};