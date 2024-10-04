/*
 * INSTRUCTION:
 *     This is a Java staring code for hw5_2.
 *     When you finish the development, download this file and and submit to Canvas
 *     according to the submission instructions.

 *     Please DO NOT change the name of the main class "Main"
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Abstract: Describe the main ideas of the program.
 * Name: Write your name
 * Date: MM/DD/YYYY
 */

import java.util.ArrayList;
import java.util.Scanner;

class Main
{

    public static class hashTable {
        public int tableSize;
        public int keySize;
        public int primeMod;
        public double loadFactor;
        public Integer[] hashList;

        hashTable (int tableSize, int keySize, double loadFactor) {
            this.tableSize = getNextPrime(tableSize);
            this.keySize = keySize;
            this.primeMod = getNextPrime(tableSize);
            this.loadFactor = loadFactor;
            this.hashList = new Integer[this.primeMod];
        }

        hashTable (int tableSize) {
            this.tableSize = getNextPrime(tableSize);
            this.keySize = 0;
            this.primeMod = getNextPrime(tableSize);
            this.loadFactor = 0;
            this.hashList = new Integer[this.primeMod];
        }

        hashTable () {
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

        public boolean isEmptyByKey(int key) {
            if(this.hashList[key%this.primeMod] == null) {
                return true;
            }
            return false;
        }

        public boolean isEmptyByIndex(int index) {
            if(this.hashList[index] == null) {
                return true;
            }
            return false;
        }

        public void increaseKeySize() {
            this.keySize++;
            this.loadFactor = (double)this.keySize/(double)this.tableSize;
            if(this.loadFactor > .5) {
                this.resize();
            }
        }

        public void resize() {
            hashTable hTableTemp = new hashTable(this.tableSize * 2);
            for(int i = 0; i < this.tableSize; i++) {
                if (this.hashList[i] != null) {
                    hashInsert(hTableTemp, this.hashList[i]); //BE WARY OF LOOPS
                }
            }
            this.hashList = hTableTemp.hashList;
            this.tableSize = hTableTemp.tableSize;
            this.keySize = hTableTemp.keySize;
            this.primeMod = hTableTemp.primeMod;
            this.loadFactor = hTableTemp.loadFactor;
        }

    }



    public static void hashInsert(hashTable hTable, int key) {
        //System.out.println("Key: " + key + " Prime Mod: " + hTable.primeMod);
        int index = key% hTable.primeMod;
        while(!hTable.isEmptyByIndex(index)) {
            index++;
        }

        hTable.hashList[index] = key;
        hTable.increaseKeySize();
    }

    public static void hashDisplay(hashTable hTable, int index) {
        if (hTable.hashList[index] == null) {
            System.out.println("Empty");
        } else {
            System.out.println(hTable.hashList[index]);
        }

        /*
        for(int i = 0; i < hTable.tableSize; i++) {
            if (hTable.hashList[i] != 0) {
                System.out.println(i + " - " + hTable.hashList[i]);

            } else {
                System.out.println(i + " - Empty");
            }
        }*/
    }

    public static void hashDisplaySize(hashTable hTable) {
        System.out.println(hTable.tableSize);
    }

    public static void hashSearch(hashTable hTable, int key) {
        int index = key%hTable.primeMod;
        boolean isFound = true;
        if(hTable.isEmptyByIndex(index)) {
            isFound = false;
        } else {
            while (hTable.hashList[index] != key) {
                index++;
                if (hTable.hashList[index] == null) {
                    isFound = false;
                    break;
                }
            }
        }
        if(isFound) {
            System.out.println(hTable.hashList[index] + " Found");
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

        hashTable hTable = new hashTable(tableSize, keySize, loadFactor);

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
            }

        }


    }
}

