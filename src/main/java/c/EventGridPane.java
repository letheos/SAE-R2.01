package c;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import m.*;
import javafx.scene.control.Button;

public class EventGridPane implements EventHandler<Event> {
    private EvenementsMenu evenementsMenu;
    private GridPane gridPane;
    private Labyrinthe laby;

    public EventGridPane(EvenementsMenu evenementsMenu, Labyrinthe laby, GridPane gridPane) {
        this.evenementsMenu = evenementsMenu;
        this.laby = laby;
        this.gridPane = gridPane;
    }

    @Override
    public void handle(Event event) {
        String racineProjet = System.getProperty("user.dir");
        String cheminHerbe = racineProjet + "\\src\\images\\Herbe.png";
        String cheminMur = racineProjet + "\\src\\images\\Mur.png";
        String chemincactus = racineProjet + "\\src\\images\\cactus.jpg";
        String cheminMargeurite = racineProjet + "\\src\\images\\Margeurites.jpg";
        String cheminTerre = racineProjet + "\\src\\images\\Terre.png";
        String cheminMouton = racineProjet + "\\src\\images\\Mouton.png";
        String cheminLoup = racineProjet + "\\src\\images\\Loup.png";
        Image image = new Image(cheminHerbe);
        Image image2 = new Image(cheminMur);
        Image image3 = new Image(chemincactus);
        Image image4 = new Image(cheminMargeurite);
        Image image5 = new Image(cheminTerre);
        Image image7 = new Image(cheminMouton);
        Image image8 = new Image(cheminLoup);
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        BackgroundImage backgroundImage3 = new BackgroundImage(image3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        BackgroundImage backgroundImage4 = new BackgroundImage(image4, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        BackgroundImage backgroundImage5 = new BackgroundImage(image5, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        Background background2 = new Background(backgroundImage2);
        Background background3 = new Background(backgroundImage3);
        Background background4 = new Background(backgroundImage4);
        Background background5 = new Background(backgroundImage5);
        ImageView imageView = new ImageView(image7);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        ImageView imageView1 = new ImageView(image8);
        imageView1.setFitHeight(30);
        imageView1.setFitWidth(50);
        imageView1.setMouseTransparent(true);
        imageView1.setPreserveRatio(true);
        Rectangle clip1 = new Rectangle(imageView1.getFitWidth(), imageView1.getFitHeight());
        clip1.setArcWidth(10);
        clip1.setArcHeight(10);
        imageView1.setClip(clip1);
        imageView.setMouseTransparent(true);
        imageView.setPreserveRatio(true);
        Rectangle clip2 = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip2.setArcWidth(10);
        clip2.setArcHeight(10);
        imageView.setClip(clip2);
        System.out.println("x : " + GridPane.getRowIndex(((Node) event.getSource())) + " y: " + GridPane.getColumnIndex((Node) event.getSource()));


        if (event.getSource() instanceof Button) {
            Button bouton = (Button) event.getSource();
            int x = GridPane.getRowIndex(bouton);
            int y = GridPane.getColumnIndex(bouton);
            Cellule cellule = laby.GetCellule(x, y);
            if (evenementsMenu.getAction() == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setHeaderText("Aucune action sélectionnée");
                alert.setContentText("Sélectionnez une action à réaliser avant de cliquer sur les cases du labyrinthe");
                alert.showAndWait();
            }
            else if (evenementsMenu.getAction().equals("Définir sortie")) {
                if (!(x >= 1 && x < this.gridPane.getRowCount() - 1 && y >= 1 && y < this.gridPane.getColumnCount() - 1) &&
                        !(x == 0 && y == 0) &&
                        !(x == 0 && y == this.gridPane.getColumnCount() - 1) &&
                        !(x == this.gridPane.getRowCount() - 1 && y == 0) &&
                        !(x == this.gridPane.getRowCount() - 1 && y == this.gridPane.getColumnCount() - 1)) {
                    if (laby.getSortie() == null) {
                        bouton.setBackground(background);
                        laby.setSortie(cellule);
                        cellule.setÉlément(new Herbe());
                    } else if (laby.getSortie() != null) {

                        Button cible = null;
                        for (Node reference : gridPane.getChildren()) {
                            if (GridPane.getColumnIndex(reference) == laby.getSortie().getY() && GridPane.getRowIndex(reference) == laby.getSortie().getX()) {
                                cible = (Button) reference;
                                break;
                            }
                        }
                        cible.setBackground(background2);
                        laby.getSortie().setÉlément(new Mur());
                        bouton.setBackground(background);
                        cellule.setÉlément(new Herbe());
                        laby.setSortie(cellule);
                    }
                    System.out.println(laby.toString());
                } else {
                    Alert horsbord = new Alert(Alert.AlertType.INFORMATION);
                    horsbord.setTitle("Erreur");
                    horsbord.setHeaderText("Action impossible");
                    horsbord.setContentText("Vous essayez de définir une sortie alors que vous n'êtes pas en bord de carte");
                }

            } else if (x >= 1 && x < this.gridPane.getRowCount() - 1 && y >= 1 && y < this.gridPane.getColumnCount() - 1) {


                if (evenementsMenu.getAction() == null) {
                    Alert pasElement = new Alert(Alert.AlertType.INFORMATION);
                    pasElement.setTitle("Erreur");
                    pasElement.setHeaderText("Aucune action n'a été sélectionnée");
                    pasElement.showAndWait();
                } /*else if (evenementsMenu.getAction().equals("Casser Mur")) {
                    if (laby.GetCellule(x, y).getÉlément() instanceof Mur) {
                        System.out.println("C'est un mur !");
                    } else {
                        Alert pasunMur = new Alert(Alert.AlertType.INFORMATION);
                        pasunMur.setTitle("Erreur");
                        pasunMur.setHeaderText("Action impossible");
                        pasunMur.setContentText("Vous essayez de détruire un Mur alors que l'élément sélectionné n'en est pas un");
                        pasunMur.showAndWait();
                    }
                }*/ else if (evenementsMenu.getAction().equals("PoserHerbe")) {
                    bouton.setBackground(background);
                    Herbe herbe = new Herbe();
                    cellule.setÉlément(herbe);
                    System.out.println(laby.toString());
                } else if (evenementsMenu.getAction().equals("PoserMargeurite")) {
                    bouton.setBackground(background4);
                    marguerite marguerite = new marguerite();
                    cellule.setÉlément(marguerite);
                    System.out.println(laby.toString());
                } else if (evenementsMenu.getAction().equals("PoserCactus")) {
                    bouton.setBackground(background3);
                    Cactus cactus = new Cactus();
                    cellule.setÉlément(cactus);
                    System.out.println(laby.toString());

                    // TODO: Logique pour poser un cactus
                } else if (evenementsMenu.getAction().equals("PoserMouton")) {
                    if (laby.getMouton() == null) {
                        if (bouton.getGraphic() != null) {
                            Alert deuxanimaux = new Alert(Alert.AlertType.INFORMATION);
                            deuxanimaux.setTitle("Erreur");
                            deuxanimaux.setContentText("Vous essayez de placer un animal sur une case ou il y en a déja un");
                            deuxanimaux.setHeaderText("Action impossible");
                            deuxanimaux.showAndWait();
                        } else if (cellule.getÉlément() instanceof Mur) {
                            Alert animalCailloux = new Alert(Alert.AlertType.INFORMATION);
                            animalCailloux.setTitle("Erreur");
                            animalCailloux.setHeaderText("Action impossible");
                            animalCailloux.setContentText("Vous essayez de placer un animal sur un Mur , ce qui est impossible");
                            animalCailloux.showAndWait();
                        } else {
                            Mouton mouton = new Mouton(cellule);
                            laby.setMouton(mouton);
                            bouton.setGraphic(imageView);
                        }
                    } else {
                        if (bouton.getGraphic() != null) {
                            Alert deuxanimaux = new Alert(Alert.AlertType.INFORMATION);
                            deuxanimaux.setTitle("Erreur");
                            deuxanimaux.setContentText("Vous essayez de placer un animal sur une case ou il y en a déja un");
                            deuxanimaux.setHeaderText("Action impossible");
                            deuxanimaux.showAndWait();
                        } else if (cellule.getÉlément() instanceof Mur) {
                            Alert animalCailloux = new Alert(Alert.AlertType.INFORMATION);
                            animalCailloux.setTitle("Erreur");
                            animalCailloux.setHeaderText("Action impossible");
                            animalCailloux.setContentText("Vous essayez de placer un animal sur un Mur , ce qui est impossible");
                            animalCailloux.showAndWait();
                        } else {
                            Button reference = null;
                            for (Node node : gridPane.getChildren()) {
                                if (GridPane.getColumnIndex(node) == laby.getMouton().getY() && GridPane.getRowIndex(node) == laby.getMouton().getX()) {
                                    if (node instanceof Button) {
                                        reference = (Button) node;
                                        break;
                                    }
                                }
                            }
                            reference.setGraphic(null);
                            laby.getMouton().setPosition(cellule);
                            bouton.setGraphic(imageView);
                        }
                    }
                    System.out.println(laby.toString());


                    // TODO: Logique pour poser un mouton
                } else if (evenementsMenu.getAction().equals("PoserLoup")) {
                    if (laby.getLoup() == null) {
                        if (bouton.getGraphic() != null) {
                            Alert deuxanimaux = new Alert(Alert.AlertType.INFORMATION);
                            deuxanimaux.setTitle("Erreur");
                            deuxanimaux.setContentText("Vous essayez de placer un animal sur une case ou il y en a déja un");
                            deuxanimaux.setHeaderText("Action impossible");
                            deuxanimaux.showAndWait();
                        } else if (cellule.getÉlément() instanceof Mur) {
                            Alert animalCailloux = new Alert(Alert.AlertType.INFORMATION);
                            animalCailloux.setTitle("Erreur");
                            animalCailloux.setHeaderText("Action impossible");
                            animalCailloux.setContentText("Vous essayez de placer un animal sur un Mur , ce qui est impossible");
                            animalCailloux.showAndWait();
                        } else {
                            Loup loup = new Loup(cellule);
                            laby.setLoup(loup);
                            bouton.setGraphic(imageView1);
                        }
                    } else {
                        if (bouton.getGraphic() != null) {
                            Alert deuxanimaux = new Alert(Alert.AlertType.INFORMATION);
                            deuxanimaux.setTitle("Erreur");
                            deuxanimaux.setContentText("Vous essayez de placer un animal sur une case ou il y en a déja un");
                            deuxanimaux.setHeaderText("Action impossible");
                            deuxanimaux.showAndWait();
                        } else if (cellule.getÉlément() instanceof Mur) {
                            Alert animalCailloux = new Alert(Alert.AlertType.INFORMATION);
                            animalCailloux.setTitle("Erreur");
                            animalCailloux.setHeaderText("Action impossible");
                            animalCailloux.setContentText("Vous essayez de placer un animal sur un Mur , ce qui est impossible");
                            animalCailloux.showAndWait();
                        } else {
                            Button reference = null;
                            for (Node node : gridPane.getChildren()) {
                                if (GridPane.getColumnIndex(node) == laby.getLoup().getY() && GridPane.getRowIndex(node) == laby.getLoup().getX()) {
                                    if (node instanceof Button) {
                                        reference = (Button) node;
                                        break;

                                    }
                                }
                            }
                            reference.setGraphic(null);
                            laby.getLoup().setPosition(cellule);
                            bouton.setGraphic(imageView1);
                        }

                    }
                    System.out.println(laby.toString());


                } else if (evenementsMenu.getAction().equals("PoserMur")) {
                    if (bouton.getGraphic() == null) {
                        bouton.setBackground(background2);

                        Mur mur = new Mur();
                        cellule.setÉlément(mur);
                        laby.toString();
                    } else {
                        Alert MuronAnimal = new Alert(Alert.AlertType.INFORMATION);
                        MuronAnimal.setTitle("Erreur");
                        MuronAnimal.setHeaderText("Action impossible");
                        MuronAnimal.setContentText("Vous essayez de placer un Mur sur un animal ce qui est impossible , déplacez l'animal ou placez un autre élément");
                        MuronAnimal.showAndWait();
                    }

                } else {
                    System.out.println("Action non reconnue");
                }
            } else {
                Alert alertBordures = new Alert(Alert.AlertType.INFORMATION);
                alertBordures.setTitle("Erreur");
                alertBordures.setHeaderText("L'élément que vous essayez de placer est hors des limites autorisées");
                alertBordures.setContentText("Essayez autre part");
                alertBordures.showAndWait();
            }

        }
        else {
            Alert Erreur = new Alert(Alert.AlertType.INFORMATION);
            Erreur.setTitle("Erreur");
            Erreur.setHeaderText("Erreur");
            Erreur.setContentText("l'action ne proviens pas d'un bouton");
        }
    }

}
