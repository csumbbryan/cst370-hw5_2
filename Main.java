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
        public int[] hashList;

        public void increaseKeySize() {
            this.keySize++;
            this.loadFactor = this.keySize/this.tableSize;
            if(this.loadFactor > .5) {
                this.resize();
            }
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

        public void resize() {
            hashTable hTableTemp = new hashTable(this.tableSize * 2);
            for(int i = 0; i < this.tableSize; i++) {
                if (this.hashList[i] != 0) {
                    hashInsert(hTableTemp, this.hashList[i]); //BE WARY OF LOOPS
                }
            }
            this.hashList = hTableTemp.hashList;
            this.tableSize = hTableTemp.tableSize;
            this.keySize = hTableTemp.keySize;
            this.primeMod = hTableTemp.primeMod;
            this.loadFactor = hTableTemp.loadFactor;
        }

        hashTable (int tableSize, int keySize, double loadFactor) {
            this.tableSize = tableSize;
            this.keySize = keySize;
            this.primeMod = getNextPrime(tableSize);
            this.loadFactor = loadFactor;
            this.hashList = new int[this.primeMod];
        }

        hashTable(int tableSize) {
            this.tableSize = tableSize;
            this.keySize = 0;
            this.primeMod = getNextPrime(tableSize);
            this.loadFactor = 0;
            this.hashList = new int[this.primeMod];
        }

        hashTable () {
            this.tableSize = 0;
            this.keySize = 0;
            this.loadFactor = 0;
        }
    }



    public static void hashInsert(hashTable hTable, int key) {
        System.out.println("Key: " + key + " Prime Mod: " + hTable.primeMod);
        hTable.hashList[key%hTable.primeMod] = key;
        hTable.increaseKeySize();
    }

    public static void hashDisplay(hashTable hTable, int key) {
        for(int i = 0; i < hTable.tableSize; i++) {
            if (hTable.hashList[i] != 0) {
                System.out.println(i + " - " + hTable.hashList[i]);

            } else {
                System.out.println(i + " - Empty");
            }
        }
    }

    public static void hashDisplaySize(hashTable hTable) {

    }

    public static void hashSearch(hashTable hTable, int key) {

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
                case "displayStatus":
                    hashDisplay(hTable, Integer.parseInt(commandArr[1]));
                case "tableSize":
                    hashDisplaySize(hTable);
                case "search":
                    hashSearch(hTable, Integer.parseInt(commandArr[1]));
            }

        }


    }
}

