package com.xmen.meli.hhfm.service;

import com.xmen.meli.hhfm.model.MutantRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LookingForMutants {
    String[] CODES_IS_MUTANT = {"AAAA", "TTTT", "CCCC", "GGGG"};
    String columnFromRow = "";
    boolean mutantDetected;
    int isMutantCount = 0;
    char[][] matrix;

    public boolean checkDNA(MutantRequest arrayDNARequest) {
        String [] arrayDNA = arrayDNARequest.dna;
        matrix = new char[arrayDNA.length][arrayDNA.length];
        mutantDetected= false;
        isMutantCount = 0;
        mutantDetected = checkRowsDNA(arrayDNARequest);
        mutantDetected = checkColumnsDNA(arrayDNA);
        mutantDetected = checkDiagonals(arrayDNARequest);
        return mutantDetected;
    }

    public boolean checkRowsDNA(MutantRequest arrayDNARequest) {
        boolean checkRowsDNA = false;
        String [] arrayDNA = arrayDNARequest.dna;
        //System.out.println("ROW TO TEST " + arrayDNA);
        for (String row : arrayDNA) {
            for (String dnaMutant : CODES_IS_MUTANT) {
                //System.out.println("CHECK ROW DNA MUTANT:  " + row.indexOf(dnaMutant));
                if (row.toUpperCase().indexOf(dnaMutant) != -1) isMutantCount++;
                //System.out.println("TEST= " + isMutantCount);
            }
        }
        checkRowsDNA = checkIsMutant(isMutantCount);
        return checkRowsDNA;
    }

    public boolean checkColumnsDNA(String [] arrayDNA){
        boolean checkColumnDNA = false;
        List<String> listDNA = new ArrayList<String>();

        for(int rowIndex=0;rowIndex<arrayDNA.length;rowIndex++) {
            for (int columnIndex = 0; columnIndex < arrayDNA.length; columnIndex++) {
                columnFromRow = columnFromRow + arrayDNA[columnIndex].toUpperCase().toCharArray()[rowIndex];
            }
            listDNA.add(columnFromRow);
            columnFromRow = "";
        }
        System.out.println("TEST= " + listDNA);
        for(int columnIndex=0;columnIndex<arrayDNA.length;columnIndex++) {
            for (String dnaMutant: CODES_IS_MUTANT) {
                System.out.println("List : " + listDNA.get(columnIndex));
                if (listDNA.get(columnIndex).indexOf(dnaMutant) != -1) isMutantCount++;
                System.out.println("TEST= " + isMutantCount);
                if(checkColumnDNA) break;
            }
            checkColumnDNA = checkIsMutant(isMutantCount);
        }

        return checkColumnDNA;
    }

    public boolean checkDiagonals(MutantRequest arrayDNARequest){
        boolean checkDiagonalDNA = false;
        String[] arrayDNA = arrayDNARequest.dna;
        //System.out.println("ROW TO TEST " + arrayDNA.length);
        String primaryDiagonalTop = "";
        String primaryDiagonalBottom = "";
        String secondaryDiagonalTop = "";
        String secondaryDiagonalBottom = "";

        List<String> listDiagonalDNA = new ArrayList<String>();
        convertToMatrix(arrayDNA);

        for(int subDiagonal=0;subDiagonal<arrayDNA.length;subDiagonal++) {
            for (int indexRow = 0; indexRow < arrayDNA.length; indexRow++) {
                for (int indexColumn = 0; indexColumn < arrayDNA.length; indexColumn++) {
                    // SECUNDARY LEFT WORK
                    if ((indexRow - indexColumn) == subDiagonal) secondaryDiagonalBottom += matrix[indexRow][indexColumn];
                    if ((indexRow - indexColumn) == -subDiagonal) secondaryDiagonalTop += matrix[indexRow][indexColumn];

                    // PRIMARY RIGHT WORK
                    if((indexRow + indexColumn)== (arrayDNA.length-1)-subDiagonal)primaryDiagonalTop += matrix[indexRow][indexColumn];
                    if((indexRow + indexColumn)== (arrayDNA.length-1)+subDiagonal)primaryDiagonalBottom += matrix[indexRow][indexColumn];
                }

            }
            listDiagonalDNA.add(primaryDiagonalTop);
            listDiagonalDNA.add(primaryDiagonalBottom);
            listDiagonalDNA.add(secondaryDiagonalTop);
            listDiagonalDNA.add(secondaryDiagonalBottom);
            primaryDiagonalBottom = "";
            primaryDiagonalTop = "";
            secondaryDiagonalTop="";
            secondaryDiagonalBottom="";
        }
        // remove repeated fields
        listDiagonalDNA.remove(0);
        listDiagonalDNA.remove(2);

        for(int diagonalIndex=0;diagonalIndex<listDiagonalDNA.size();diagonalIndex++) {
            for (String dnaMutant: CODES_IS_MUTANT) {
                //System.out.println("List : " + listDiagonalDNA.get(diagonalIndex));
                //  if != -1 is mutant
                if (listDiagonalDNA.get(diagonalIndex).indexOf(dnaMutant) != -1) isMutantCount++;
                //System.out.println("TEST= " + isMutantCount);
                if(checkDiagonalDNA) break;
            }
            checkDiagonalDNA = checkIsMutant(isMutantCount);
        }
        printMatrix(arrayDNA);
        return checkDiagonalDNA;
    }

    public char[][] convertToMatrix(String[] arrayDNA){
        for(int row=0;row<arrayDNA.length;row++){
            for(int column=0;column<arrayDNA.length;column++) {
                matrix[row][column] = arrayDNA[row].toUpperCase().toCharArray()[column];
            }
        }
        return matrix;
    }
    public void printMatrix(String[] arrayDNA){
        System.out.println("\nArray nxn");
        for(int i=0;i<arrayDNA.length;i++) {
            for(int j=0;j<arrayDNA.length;j++){
                System.out.print(" - "+ matrix[i][j]);
            }
            System.out.println();
        }
    }
    public boolean checkIsMutant( int isMutantCount) {
        mutantDetected = (isMutantCount>1)? true: false;
        return mutantDetected;
    }

}
