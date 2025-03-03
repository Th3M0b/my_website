# `stack.h` Naudojimo Instrukcija

Ši dokumentacija paaiškina, kaip naudotis `stack.h` biblioteka, kuri įgyvendina **steko** struktūrą (angl. *stack*) remiantis **cikliniu susietu sąrašu** (angl. *cyclic linked list*). Stekas veikia pagal **LIFO** (Last In, First Out) principą, tai yra, paskutinis įdėtas elementas bus pirmas, kuris bus pašalintas.

gcc -c stack.c stack_test.c list.c && gcc stack_test.o stack.o list.o -o test_stack.exe

---

## Funkcijos

### 1. `Stack create(void)`
Sukuria ir grąžina naują tuščią steką.   
- `Stack` – struktūra, kurioje yra steko dydis (`size`) ir rodyklė į viršutinį elementą (`head`).

**Pavyzdys**:
```c
Stack myStack = create();
```

---

### 2. `int destroy(Stack *stack)`

Sunaikina steką ir atlaisvina visą su juo susijusią atmintį.  

- `stack` – rodyklė į steką, kurį norima sunaikinti.


**Pavyzdys**:

```c
destroy(&myStack);
```

---

### 3. `bool isEmpty(const Stack stack)`
Patikrina, ar stekas yra tuščias.  

- `stack` – stekas, kurio būsena tikrinama.

**Pavyzdys**:
```c
if (isEmpty(myStack)) {
    printf("Stekas tuščias.\n");
}
```

---

### 4. `bool isFull(const Stack stack)`
Patikrina, ar stekas yra pilnas. Ši funkcija tikrina, ar pavyko išskirti atmintį naujam elementui.  

- `stack` – stekas, kurio būsena tikrinama.

**Pavyzdys**:
```c
if (isFull(myStack)) {
    printf("Stekas pilnas.\n");
}
```

---

### 5. `int push(Stack *stack, const Item item)`
Prideda naują elementą į steko viršų.  

- `stack` – rodyklė į steką, į kurį norima pridėti elementą.
- `item` – reikšmė, kuri bus pridėta į steką.

**Pavyzdys**:
```c
push(&myStack, 10);
```

---

### 6. `int pop(Stack *stack, Item **item)`
Pašalina ir grąžina elementą iš steko viršaus.  

- `stack` – rodyklė į steką, iš kurio norima pašalinti elementą.
- `item` – rodyklė į rodyklę, kurioje bus grąžinta pašalinto elemento reikšmė.

**Pavyzdys**:
```c
Item *value;
if (pop(&myStack, &value) == 0) {
    printf("Išimta reikšmė: %d\n", *value);
    free(value);
}
```

---

## Steko Struktūra

Kiekvienas steko elementas yra `Node` struktūra, kuri turi:
- Rodyklę į kitą elementą.
- Rodyklę į duomenis (`Item`).

**ciklinis susietas sąrašas** reiškia, kad paskutinio elemento rodyklė nukreipia į pirmąjį elementą.

---

## Grąžinamos Reikšmės ir Klaidos

- **Sėkmė**: Dauguma funkcijų grąžina `0`, jei operacija atlikta sėkmingai.
- **Klaidos**: 
  - `-1` – neteisingi parametrai (pvz., `NULL` rodyklė).
  - `-2` – atminties išskyrimo klaida.
  - `-3` – sąrašo manipuliavimo klaida.

---

## Pavyzdžiai

### Steko Sukūrimas ir Elementų Pridėjimas
```c
Stack myStack = create();
push(&myStack, 5);
push(&myStack, 10);
```

### Elementų Išėmimas
```c
Item *value;
while (!isEmpty(myStack)) {
    if (pop(&myStack, &value) == 0) {
        printf("Išimta: %d\n", *value);
        free(value);
    }
}
```

### Steko Sunaikinimas
```c
destroy(&myStack);
```

---
Ši dokumentacija padės jums efektyviai naudotis `stack.h` biblioteka ir išvengti dažniausių klaidų.
