/*
 * INSTRUCTION:
 *     This is a Java staring code for hw5_2.
 *     When you finish the development, download this file and and submit to Canvas
 *     according to the submission instructions.

 *     Please DO NOT change the name of the main class "Main"
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Abstract: Program accepts input from user to create and update hash tables based on a series of
 * instructions. The initial hash table is set by a defined hash table size, then expanded as needed based on
 * the load factor. Instructions allow insertion and retrieval of the hash table and its keys.
 * Name: Bryan Zanoli
 * Date: 10/08/2024
 */

import java.util.Scanner;

class Main
{

    public static class HashTable {
        public int tableSize;
        public int keySize;
        //public int primeMod;
        public double loadFactor;
        public Integer[] hashList;

        HashTable(int tableSize, int keySize, double loadFactor) {
            this.tableSize = getNextPrime(tableSize);
            this.keySize = keySize;
            //this.primeMod = getNextPrime(tableSize);
            this.loadFactor = loadFactor;
            this.hashList = new Integer[this.tableSize];
        }

        HashTable(int tableSize) {
            this.tableSize = getNextPrime(tableSize);
            this.keySize = 0;
            //this.primeMod = getNextPrime(tableSize);
            this.loadFactor = 0;
            this.hashList = new Integer[this.tableSize];
        }

        HashTable() {
            this.tableSize = 0;
            this.keySize = 0;
            this.loadFactor = 0;
        }

        public static int getNextPrime(int number) {
            int pNum = number;
            for(int i = 2; i < pNum; i++) {
                if(pNum%i == 0) {
                    pNum++;
                    i = 2;
                }
            }
            return pNum;
        }

        public int getTableSize() {
            return this.tableSize;
        }

        public int getKeyByIndex(int index) {
            return this.hashList[index];
        }

        public boolean isEmptyByKey(int key) {
            if(this.hashList[key%this.tableSize] == null) {
                return true;
            }
            return false;
        }

        public boolean isEmptyByIndex(int index) {
          return this.hashList[index] == null;
        }

        public void increaseKeySize() {
            this.keySize++;
            this.loadFactor = this.tableSize == 0 ? 0 : (double)this.keySize/(double)this.tableSize;
            if(this.loadFactor > .5) {
                this.resize();
            }
        }

        public void addKey(int key) {
            int index = key% this.tableSize;
            while(!this.isEmptyByIndex(index)) {
                index++;
            }
            this.hashList[index] = key;
            this.increaseKeySize();
        }

        public void resize() {
            HashTable hTableTemp = new HashTable(this.tableSize * 2);
            for(int i = 0; i < this.tableSize; i++) {
                if (this.hashList[i] != null) {
                    hashInsert(hTableTemp, this.hashList[i]); //BE WARY OF LOOPS
                }
            }
            this.hashList = hTableTemp.hashList;
            this.tableSize = hTableTemp.tableSize;
            this.keySize = hTableTemp.keySize;
            //this.primeMod = hTableTemp.primeMod;
            this.loadFactor = hTableTemp.loadFactor;
        }
    }



    public static void hashInsert(HashTable hTable, int key) {
        //System.out.println("Key: " + key + " Prime Mod: " + hTable.primeMod);
        /*
        int index = key% hTable.tableSize;
        while(!hTable.isEmptyByIndex(index)) {
            index++;
        }

        hTable.hashList[index] = key;
        hTable.increaseKeySize();
         */
        hTable.addKey(key);
    }

    public static void hashDisplay(HashTable hTable, int index) {
        if (hTable.isEmptyByIndex(index)) {
            System.out.println("Empty");
        } else {
            System.out.println(hTable.getKeyByIndex(index));
        }
    }

    public static void hashDisplaySize(HashTable hTable) {
        System.out.println(hTable.getTableSize());
    }

    public static void hashDisplayAll(HashTable hTable) {
        for(int i = 0; i < hTable.getTableSize(); i++) {
            if (!hTable.isEmptyByIndex(i)) {
                System.out.println(i + " - " + hTable.getKeyByIndex(i));

            } else {
                System.out.println(i + " - Empty");
            }
        }
    }

    public static void hashSearch(HashTable hTable, int key) {
        int index = key% hTable.getTableSize();
        boolean isFound = true;
        if(hTable.isEmptyByIndex(index)) {
            isFound = false;
        } else {
            while (hTable.getKeyByIndex(index) != key) {
                index++;
                if (hTable.isEmptyByIndex(index)) {
                    isFound = false;
                    break;
                }
            }
        }
        if(isFound) {
            System.out.println(key + " Found");
        } else {
            System.out.println(key + " Not found");
        }
    }

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        int tableSize = Integer.parseInt(scanner.nextLine());
        int commandCount = Integer.parseInt(scanner.nextLine());
        int keySize = 0;
        double loadFactor =0.0;

        HashTable hTable = new HashTable(tableSize, keySize, loadFactor);

        for(int i = 0; i < commandCount; i++) {
            String[] commandArr = scanner.nextLine().split(" ");
            switch (commandArr[0]) {
                case "insert":
                    hashInsert(hTable, Integer.parseInt(commandArr[1]));
                    break;
                case "displayStatus":
                    hashDisplay(hTable, Integer.parseInt(commandArr[1]));
                    break;
                case "tableSize":
                    hashDisplaySize(hTable);
                    break;
                case "search":
                    hashSearch(hTable, Integer.parseInt(commandArr[1]));
                    break;
                case "displayAll":
                    hashDisplayAll(hTable);
                    break;
            }

        }


    }
}

