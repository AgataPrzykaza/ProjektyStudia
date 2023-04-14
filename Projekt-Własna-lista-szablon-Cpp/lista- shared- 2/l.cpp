
#include <iostream>
#include<fstream>
#include<memory>
#include <string>
#include <sstream>

using namespace std;

template<class T>
struct Node
{
	T value;
	shared_ptr<Node> next;

};


template<class T>
class lista
{
private:

	shared_ptr<Node<T>> head;
public:
	lista()
	{
		head = NULL;

	};
	~lista()
	{
		while (head)
			head = move(head->next);
	};

	lista(const lista& i) :head(nullptr) //konstruktor kopiujący
	{
		if (i.head != nullptr)
		{   
			try {
					head = make_shared<Node<T>>();
			}
			
			catch (exception& e)
			{
				cout << "Blad pamieci\n";
			}
			head->value = i.head->value;

			shared_ptr<Node<T>> p1 = head;
			shared_ptr<Node<T>> p2 = i.head->next;

			while (p2)
			{
				try {
						p1->next = make_shared<Node<T>>();
				}
				catch (exception& e)
				{
					cout << "Blad pamieci\n";
				}
				p1 = p1->next;
				p1->value = p2->value;

				p2 = p2->next;
			}
			//cout << "kop";
		}
		

	}

	lista(lista&& i):head(nullptr) //konstruktor przenoszący
	{
		swap(head, i.head);
	}


	lista& operator=(const lista& i)//operator przypisania
	{
		if (i.head == nullptr)
			return *this;
		if (this == &i)
			return *this;
		else
		{
			try {
					head = make_shared<Node<T>>();
				}
			catch (exception& e)
			{
				cout << "Blad pamieci\n";
			}
			
			head->value = i.head->value;

			shared_ptr<Node<T>> p1 = head;
			shared_ptr<Node<T>> p2 = i.head->next;

			while (p2)
			{  try
				{
					p1->next = make_shared<Node<T>>();
				}
				catch (exception& e)
				{
					cout << "Blad pamieci\n";
				}
				
				p1 = p1->next;
				p1->value = p2->value;

				p2 = p2->next;
			}

			return *this;
		}
	}

	lista& operator=(lista&& i) // przeniesienia
	{
		if (this == &i)
			return *this;
		swap(head, i.head);
		return *this;
	}

	friend ostream& operator<<(ostream& s, const lista<T>& l)
	{
		lista<T> tmp = l;

		while (tmp.size() != 0)
		{
			s <<tmp.front()<<" ";
			tmp.pop();
		}

		return s;
	}

	friend istream& operator>>(istream& s, lista<T>& l)
	{
		while (!s.eof())
		{
		T element;
		s >> element;
		l.push(element);
		}
		

		return s;
	}


	void remove(T element) //usuniecie wszystkich takich elementow
	{
		shared_ptr<Node<T>> prev = head;
		shared_ptr<Node<T>> current = head->next;
		bool found = 0;

		if (head == nullptr)
		{
			return;
		}

		if (head->value == element)
		{
			head = head->next;
			return;
		}

		while (current != nullptr)
		{
			if (current->value == element)
			{
				prev->next = current->next;

				current = current->next;
				found = 1;
			}
				

			else
			{
				prev = current;
				current = current->next;
			}
			

		}
		if (found==0)
			cout << "no such elemrnt was there";

	}


	void remove1(T element) //usuniecie 1 elementu
	{
		shared_ptr<Node<T>> prev = head;
		shared_ptr<Node<T>> current = head->next;

		if (head->value == element)
		{
			head = head->next;
			return;
		}

		while (current != nullptr)
		{
			if (current->value == element)
				break;
			else
			{
				prev = current;
				current = current->next;
			}
		}
		if (current == NULL)
			cout << "no such elemrnt was there";

		else
		{
			prev->next = current->next;
		}

	}

	void insertSort(bool how) //sortowanie
	{

		if (head == nullptr || head->next == nullptr)
		{
			return;
		}
		shared_ptr<Node<T>> sorted=nullptr;

		shared_ptr<Node<T>> current = head;

		while (current)
		{
			shared_ptr<Node<T>> second = current->next;
			if (how == 0)
				sortNode(current, sorted);
			else
				sortNodeMalejaco(current, sorted);
			current = second;
		}

		head = sorted;

	}

	void sortNode(shared_ptr<Node<T>> &newNode,shared_ptr<Node<T>> &sorted)
	{
		if (sorted == nullptr || sorted->value >= newNode->value)
		{
			newNode->next = sorted;
			sorted = newNode;
		}

		else
		{
			shared_ptr<Node<T>> current = sorted;

			while (current->next != nullptr && current->next->value < newNode->value)
			{
				current = current->next;

			}
			newNode->next = current->next;
			current->next = newNode;
		}
	}
		
	void sortNodeMalejaco(shared_ptr<Node<T>>& newNode, shared_ptr<Node<T>>& sorted)
	{
		if (sorted == nullptr || sorted->value <= newNode->value)
		{
			newNode->next = sorted;
			sorted = newNode;
		}

		else
		{
			shared_ptr<Node<T>> current = sorted;

			while (current->next != nullptr && current->next->value > newNode->value)
			{
				current = current->next;

			}
			newNode->next = current->next;
			current->next = newNode;
		}
	}
	bool search(T element) //wyszukanie elementu
	{
		if (head == nullptr)
		{
			//cout << "not found";
			return 0;
		}

		shared_ptr<Node<T>> current = head;
		int i = 0;
		while (current)
		{
			if (current->value == element)
			{
				//cout << "na pozycji" << i << endl;
				return 1;
			}
			current = current->next;
			i++;
		}
		//cout << "not found";
		return 0;

	}

	void push(T element) //do danie na poczatek
	{
		shared_ptr<Node<T>> newNode;
		try
		{
			newNode = make_shared<Node<T>>();
	}
		catch (exception& e)
		{
			cout << "Blad pamieci\n";
		}
		newNode->value = element;
		if (head == nullptr)
		{
			head = move(newNode);
		}
		else
		{
			newNode->next = move(head);
			head = move(newNode);
		}

	}
	void pop() //usuniecie pierwszego
	{
		if (head == nullptr)
		{
			cout << "juz pusta";
		}
		else
		{
			head = move(head->next);
		}

	}

	T front() //pierwszy element z listy
	{
		if (head == nullptr)

		{
			//cout << "pusty";
			return head->value;
		}

		else

		{
			//cout << head->value << endl;
			return head->value;
		}
	}

	bool empty()
	{
		if (head == nullptr)
			return 1;
		return 0;
	}
	void show()// pokazanie listy
	{
		shared_ptr<Node<T>> tmp = head;
		while (tmp)
		{
			cout << tmp->value << endl;
			tmp = tmp->next;
		}
		cout << endl;
	}

	int size()
	{
		int i = 0;
		shared_ptr<Node<T>> tmp = head;
		while (tmp)
		{
			i++;
			tmp = tmp->next;
		}
		return i;
	}


	void zapisBin(const char* nazwa) //serializacja
	{
		ofstream plik(nazwa, ios::binary);
		
		shared_ptr<Node<T>> k = head;
		while (k)
		{
			T ele = k->value;
			k = k->next;
			plik.write((const char*)&ele, sizeof ele);
		}
		plik.close();
	}


	void deser(const char* nazwa) //dwa razy jedno bierze
	{
		ifstream plik(nazwa, ios::binary);
		
		while (head)
			head = head->next;

		//lista<T> l;
		T ele;
		while (!plik.eof())

		{
			plik.read((char*)&ele, sizeof ele);
			push(ele);
		}
			
			//head = l.head;
		plik.close();

	}
	
	
};

template<>
void lista<string>::zapisBin(const char* nazwa)
{
	ofstream plik(nazwa, ios::binary);
	
	shared_ptr<Node<string>> k = head;
	while (k)
	{
		string ele = k->value;
		k = k->next;
		plik.write(ele.c_str(), ele.size()+1);
	}
	plik.close();
}

template<>
void lista<string>::deser(const char* nazwa)// jako jeden wyraz robi
{
	ifstream plik(nazwa, ios::binary);

	while (head)
		head = head->next;

	lista<string> l;
	string str;
	getline(plik, str,'\0');
	stringstream ss(str);
	while (!ss.eof())
	{
		string s;
		ss >> s;
		
		l.push(s);
	}
	
	
	head = l.head;
	plik.close();

}
/************************************************************************************************************/
class osoba
{
public:
	string pesel;
	string imie;
	string nazwisko;
	
	string adres;


	inline bool operator==(const osoba& o1)
	{
		if (pesel == o1.pesel && imie == o1.imie && nazwisko == o1.nazwisko && adres == o1.adres)
			return 1;
		return 0;

	}

	inline bool operator<=(const osoba& o1)
	{
		 return nazwisko <= o1.nazwisko ? 1: 0;

	}
	inline bool operator<(const osoba& o1)
	{
		return nazwisko < o1.nazwisko ? 1 : 0;

	}
	inline bool operator>(const osoba& o1)
	{
		return nazwisko > o1.nazwisko ? 1 : 0;
		

	}

	inline bool operator>=(const osoba& o1)
	{
		return nazwisko >= o1.nazwisko? 1 : 0;
	}

	void set(string p, string i, string n, string m)
	{
		pesel = p;
		imie = i;
		nazwisko = n;
		adres = m;
	}
	friend istream& operator >> (istream& s, osoba& o)
	{
		s >> o.pesel >> o.imie >> o.nazwisko >> o.adres;
		return s;
	}

	friend ostream& operator << (ostream& s, const osoba& o)
	{
		s << o.pesel << " " << o.imie << " " << o.nazwisko << " " << o.adres;
		return s;
	}

	osoba() :pesel(""), imie(""), nazwisko(""), adres("") {};
	osoba(string p, string i, string n, string a) :pesel(p), imie(i), nazwisko(n), adres(a) {};

};
class Dane
{
private:
	lista<osoba> dane;
	lista<string> miasta;

public:
	
	Dane() {};
	~Dane() {};
	Dane(lista<osoba> d) :dane(d) {};
	Dane(lista<osoba> d,lista<string>miasto) :dane(d), miasta(miasto) {};
	
	lista<osoba> NaMiescie(string ulica) //ok
	{
		lista<osoba> o;
		lista<osoba> tmp(dane);
		if (miasta.search(ulica))
		{
			for (int i = 0; i < dane.size(); i++)
			{
				if (tmp.front().adres == ulica)
				{
					
					o.push(tmp.front());
				}

				tmp.pop();
			}
		
		}
		if (o.empty())
			cout << "Nie ma miasta takiego";
			return o;

	}

	void UsuniecieUcznia(osoba o)//ok
	{
		if (dane.search(o))
		{
			dane.remove(o);
		}
	}
	void DodanieUcznia(osoba o)//ok
	{
		dane.push(o);

	}
	void PokazUczniow()//ok
	{
		lista<osoba> tmp(dane);
		for (int i = 0; i < dane.size(); i++)
		{
			cout << tmp.front()<<endl;
			tmp.pop();
		}
	}
	void doPlik(string nazwa) //ok
	{
		ofstream plik(nazwa);

		lista<osoba> tmp(dane);

		while (!tmp.empty())
		{
			plik << tmp.front() << endl;
			tmp.pop();
		}

		plik.close();

	}

	void zPliku(string nazwa)// ok
	{
		ifstream plik(nazwa);
		string smiec;

		while (!plik.eof())
		{  
			osoba tmp;
			plik >> tmp;
			miasta.push(tmp.adres);
			dane.push(tmp);
		}
		miasta.pop();
		dane.pop();
		plik.close();
	}

	void SortowanieAlf(int i)
	{
		if (dane.empty())
			return;
		dane.insertSort(i);
	}

};

class ocena
{
public:
	string pesel;
	string przedmiot;
	int grade;
	string rodzaj;

 
	ocena():pesel(""),przedmiot(""),grade(0),rodzaj("") {};
	ocena(string p, string prze, int g, string r) :pesel(p), przedmiot(prze), grade(g), rodzaj(r) {};

	friend istream& operator >> (istream& s, ocena& o)
	{
		s >> o.pesel >> o.przedmiot >> o.grade >> o.rodzaj;
		return s;
	}

	friend ostream& operator << (ostream& s, const ocena& o)
	{
		s << o.pesel << " " << o.przedmiot << " " << o.grade << " " << o.rodzaj;
		return s;
	}
	inline bool operator==(const ocena& o1)
	{
		if (pesel == o1.pesel && przedmiot == o1.przedmiot && grade == o1.grade && rodzaj == o1.rodzaj)
			return 1;
		return 0;

	}
	inline bool operator<=(const ocena& o1)
	{
		return przedmiot <= o1.przedmiot ? 1 : 0;

	}
	inline bool operator<(const ocena& o1)
	{
		return przedmiot < o1.przedmiot ? 1 : 0;

	}
	inline bool operator>(const ocena& o1)
	{
		return przedmiot > o1.przedmiot ? 1 : 0;


	}

	inline bool operator>=(const ocena& o1)
	{
		return przedmiot >= o1.przedmiot ? 1 : 0;
	}
};
class Dziennik
{
private:
	lista<ocena> oceny;
public:
	Dziennik():oceny() {};
	Dziennik(lista<ocena> o) :oceny(o) {};

	double SredniaZprzedmiotu(string uczen, string topic)
	{
		lista<ocena> tmp(oceny);
		
		double suma = 0;
		int ile = 0;
		while (!tmp.empty())
		{
			if (uczen == tmp.front().pesel && topic == tmp.front().przedmiot)
			{
				suma += tmp.front().grade;
				
				ile++;
			}
			tmp.pop();
		}

		if (ile == 0)
			return 0;
		else
			return suma / ile;
		

	}

	void zPliku(string nazwa)// ok
	{
		ifstream plik(nazwa);
		string smiec;

		while (!plik.eof())
		{
			ocena tmp;
			plik >> tmp;
			
			oceny.push(tmp);
		}
		oceny.pop();
		plik.close();
	}

	void doPlik(string nazwa) //ok
	{
		ofstream plik(nazwa);

		lista<ocena> tmp(oceny);

		while (!tmp.empty())
		{
			plik << tmp.front() << endl;
			tmp.pop();
		}

		plik.close();

	}

	lista<string> SredniaWyzsza(int o,string topic)
	{
		lista<ocena> tmp(oceny);
		lista<string> osoby;
		while (!tmp.empty())
		{
			if (SredniaZprzedmiotu(tmp.front().pesel, topic) > o)
			{ 
				if(osoby.search(tmp.front().pesel)==false)
				osoby.push(tmp.front().pesel);
				
				
			}
			tmp.pop();	
		}
		return osoby;
	}

	void PokaOceny()//ok
	{
		lista<ocena> tmp(oceny);
		while (!tmp.empty())
		{
			cout << tmp.front() << endl;
			tmp.pop();
		}
	}

	void DodanieOceny(ocena o)//ok
	{
		oceny.push(o);

	}
	void UsuniecieOcena(ocena o)//ok
	{
		if (oceny.search(o))
		{
			oceny.remove(o);
		}
	}
	void SortowanieAlf(int i)
	{
		if (oceny.empty())
			return;
		oceny.insertSort(i);
	}

};
int main(int argc,char** argv)
{
	
	if (argc > 1)
	{  string g = argv[1];
		if (g == "typy")
		{

			lista<int> calkowite;
			cout << "Podaj 7 liczby calkowitych" << endl;
			for (int i = 0; i < 7; i++)
			{
				int tmp;
				cin >> tmp;

				while (cin.fail())
				{
					cout << "Zly typ, jeszce raz: ";
					cin.clear();
					cin.ignore(1000, '\n');
					cin >> tmp;
				};
				calkowite.push(tmp);
			}
			calkowite.show();
			cout << endl << "Posortowane: 1- rosnaco, 2- malejaco:  ";

			int opcje = 3;
			cin >> opcje;
			while (!(opcje == 1 || opcje == 2))
			{
				cout << "Podaj jeszcze raz" << endl;
				cin.clear();
				cin.ignore(1000, '\n');
				cin >> opcje;
			}

			if (opcje == 1)
			{
				calkowite.insertSort(0);
				cout << endl;
				calkowite.show();
			}
			else
			{
				calkowite.insertSort(1);
				cout << endl;
				calkowite.show();

			}
			cout << "Sprawdz czy liczba jest: ";
			cin >> opcje;
			while (cin.fail())
			{
				cout << "Zly typ, jeszce raz: ";
				cin.clear();
				cin.ignore(1000, '\n');
				cin >> opcje;
			};

			if (calkowite.search(opcje))
			{
				cout << "Jest taki element" << endl;
			}
			else
				cout << "nie ma takiego elementu" << endl;
			cout << "Usun element: ";
			cin >> opcje;
			while (cin.fail())
			{
				cout << "Zly typ, jeszce raz: ";
				cin.clear();
				cin.ignore(1000, '\n');
				cin >> opcje;
			};
			calkowite.remove(opcje);
			cout << "Po usunieciu: " << endl;
			calkowite.show();
			const char* p = "calkowite.txt";

			cout << "Serializacja do pliku: " << p;
			calkowite.zapisBin(p);

			lista<int> calkowite2;
			cout << endl << "Deserializacja: " << endl;
			calkowite2.deser(p);
			calkowite2.show();

		}
		if (g == "string")
		{
			lista<string> s;
			s.push("wkwo");
			s.push("ala");
			s.push("homik");
			s.push("kol");
			s.push("ala");
			lista<string> s1(s);
			cout << "Lista wyrazow: " << endl;
			s.show();
			cout << "Usunicie z wyrazu ala";
			/*s.pop();
			cout << endl;
			s.show();*/
			cout << endl;
			s.remove("ala");

			s.show();

			cout << "Przypisanie do nowej listy:";
			s = s1;
			cout << endl;
			s.show();
			cout << "__________" << endl;
			cout << "Posortowane rosnaco: " << endl;
			s.insertSort(0);
			cout << endl;
			s.show();
			cout << endl << "malejaco:" << endl;
			s.insertSort(1);
			s.show();

			cout << "Czy jest wyraz baba: ";
			cout << s.search("baba");
			cout << endl;

			s.zapisBin("lol.bin");
			s.deser("lol.bin");
			/*cout << "Po deserializacji: " << endl;
			s.show();*/
		}
		if (g == "-ucz" || g == "-oce" || g == "-s")
		{
			if (g == "-ucz")
			{
				string plik = argv[2];

				if (plik.find(".txt") != string::npos)
				{
					Dane d;
					string p;
					string i;
					string n;
					string m;
					d.zPliku(plik);
					d.PokazUczniow();
					cout << endl;
					cout << "Dodaj ucznia do bazy" << endl;

					cout << "Pesel: ";
					cin >> p;
					while (cin.fail())
					{
						cout << "Jeszcze raz: ";
						cin.clear();
						cin.ignore(1000, '\n');
						cin >> p;
					};

					cout << "Imie: ";
					cin >> i;
					while (cin.fail())
					{
						cout << "Jeszce raz: ";
						cin.clear();
						cin.ignore(1000, '\n');
						cin >> i;
					};

					cout << "Nazwisko: ";
					cin >> n;
					while (cin.fail())
					{
						cout << "Jeszce raz: ";
						cin.clear();
						cin.ignore(1000, '\n');
						cin >> n;
					};

					cout << "Miasto: ";
					cin >> m;
					while (cin.fail())
					{
						cout << "Jeszce raz: ";
						cin.clear();
						cin.ignore(1000, '\n');
						cin >> m;
					};

					osoba o(p, i, n, m);
					d.DodanieUcznia(o);

					cout <<endl<< "Baza po dodaniu:" << endl;
					d.PokazUczniow();
					d.doPlik(plik);

					cout << "Posortuj alfabetycznie wedlug nazwiska(0-rosn,1-malejaco): ";
					int opcja;
					cin >> opcja;
					while (!(opcja == 1 || opcja == 0))
					{
						cout << "Jeszce raz: ";
						cin.clear();
						cin.ignore(1000, '\n');
						cin >> opcja;
					};
					d.SortowanieAlf(opcja);
					d.PokazUczniow();

					cout << "Usun ucznia: " << endl;
					cout << "Pesel: ";
					cin >> p;
					cout << "Imie: ";
					cin >> i;
					cout << "Nazwisko: ";
					cin >> n;
					cout << "Miasto: ";
					cin >> m;

					o.set(p, i, n, m);
					d.UsuniecieUcznia(o);
					d.PokazUczniow();

					cout << "Lista uczniow z jednego miasta " << endl;
					cout << "Miasto: ";
					cin >> m;

					d.NaMiescie(m).show();

					d.doPlik(plik);

				}
				else
				{
					cout << "nie podano nazwy pliku" << endl;
				}
			}

			if (g == "-oce")
			{
				string plik = argv[2];

				if (plik.find(".txt") != string::npos)
				{
					Dziennik baza;
					baza.zPliku(plik);
					baza.PokaOceny();
					cout << "Srednia z ucznia z przedmiotu" << endl;
					cout << "Podaj pesel: ";
					string p, u,r;
					cin >> u;
					cout << "Podaj przedmiot: ";
					cin >> p;

					cout << "srednia " << baza.SredniaZprzedmiotu(u, p);

					cout << endl << "lista uczniow z wyzsza srednia niz podana liczba" << endl;
					int l;
					cout << "Podaj liczbe ";
					cin >> l;
					while (cin.fail())
					{
						cout << "Zly typ, jeszce raz: ";
						cin.clear();
						cin.ignore(1000, '\n');
						cin >> l;
					};
					cout << "Podaj przedmiot: ";
					cin >> p;

					cout << endl;
					baza.SredniaWyzsza(l, p).show();
					cout << "Dodaj ocene"<<endl;
					cout << "Pesel: ";
					cin >> u;
					cout << "Podaj przedmiot: ";
					cin >> p;

					cout << "Podaj ocene ";
					cin >> l;
					while (cin.fail())
					{
						cout << "Zly typ, jeszce raz: ";
						cin.clear();
						cin.ignore(1000, '\n');
						cin >> l;
					};

					cout << "Rodzaj: ";
					cin >> r;

					ocena o(u, p, l, r);
					baza.DodanieOceny(o);

					cout << "Dziennik:" << endl;
					baza.PokaOceny();

					cout << "Usuniecie oceny " << endl;

					cout << "Pesel: ";
					cin >> u;
					cout << "Podaj przedmiot: ";
					cin >> p;

					cout << "Podaj ocene ";
					cin >> l;
					while (cin.fail())
					{
						cout << "Zly typ, jeszce raz: ";
						cin.clear();
						cin.ignore(1000, '\n');
						cin >> l;
					};

					cout << "Rodzaj: ";
					cin >> r;
					ocena o1(u, p, l, r);
					baza.UsuniecieOcena(o1);

					baza.PokaOceny();

					cout << "Posortuj alfabetycznie wedlug przedmiotu(0-rosn,1-malejaco): ";
					int opcja;
					cin >> opcja;
					while (!(opcja == 1 || opcja == 0))
					{
						cout << "Jeszce raz: ";
						cin.clear();
						cin.ignore(1000, '\n');
						cin >> opcja;
					};
					baza.SortowanieAlf(opcja);
					baza.PokaOceny();

					baza.doPlik(plik);

				}
				else
					cout << "nie podano nazwy pliku" << endl;

			}
		}
		else
		{
			cout << "Nie ma takiej opcji w programie" << endl;
		}
	}
	if (argc == 1)
	{
		cout << "Brak opcji, prosze wybrac ponownie" << endl;
		cout << "-ucz plik tekstowy z uczniami " << endl;
		cout << "-oce plik tekstowy z ocenami" << endl;
	}

	


};