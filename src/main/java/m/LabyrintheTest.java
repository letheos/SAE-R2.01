package m;
/*
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

class LabyrintheTest {

    private Labyrinthe labyrinthe;

    @BeforeEach
    void setUp() {
        labyrinthe = new Labyrinthe(5, 5);
    }

    @Test
    void chargerLabyrinthe() {
        try {
            // Sauvegarder le labyrinthe
            labyrinthe.sauvegarderLabyrinthe("testLabyrinthe.dat");

            // Charger le labyrinthe à partir du fichier
            Labyrinthe labyrintheChargé = Labyrinthe.chargerLabyrinthe("testLabyrinthe.dat");

            // Vérifier si les deux labyrinthes sont identiques
            assertEquals(labyrinthe.toString(), labyrintheChargé.toString());
        } catch (IOException | ClassNotFoundException e) {
            fail("Erreur lors du chargement du labyrinthe.");
        }
    }

    @Test
    void casserMur() {
        // Test avec une cellule valide
        assertTrue(labyrinthe.CasserMur(1, 1));

        // Test avec une cellule en bord de carte
        assertTrue(labyrinthe.CasserMur(0, 0));
    }

    @Test
    void poserMargueurite() {
        assertTrue(labyrinthe.PoserMargueurite(2, 2));
        assertTrue(labyrinthe.PoserMargueurite(1, 1));
    }

    @Test
    void poserCactus() {
        assertTrue(labyrinthe.PoserCactus(3, 3));
        assertTrue(labyrinthe.PoserCactus(1, 1));
    }

    @Test
    void poserHerbe() {
        assertTrue(labyrinthe.PoserHerbe(4, 4));
        assertTrue(labyrinthe.PoserHerbe(1, 1));
    }

    @Test
    void poserMur() {
        assertTrue(labyrinthe.PoserMur(1, 1));
        assertTrue(labyrinthe.PoserMur(1, 1));
    }

    @Test
    void définirSortie() {
        assertTrue(labyrinthe.DéfinirSortie(2, 2));
        assertTrue(labyrinthe.DéfinirSortie(0, 0));
    }

    @Test
    void getVoisins() {
        // Test avec une cellule sans mur autour
        ArrayList<Cellule> voisins = labyrinthe.getVoisins(labyrinthe.GetCellule(2, 2));
        assertEquals(4, voisins.size());

        // Test avec une cellule en coin de carte
        voisins = labyrinthe.getVoisins(labyrinthe.GetCellule(0, 0));
        assertEquals(2, voisins.size());
    }
}

 */