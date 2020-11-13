package ch.bzz.it.flugzeug.data;

import ch.bzz.it.flugzeug.model.User;
import ch.bzz.it.flugzeug.service.Config;
import ch.bzz.it.flugzeug.model.Flugzeug;
import ch.bzz.it.flugzeug.model.Hersteller;
import ch.bzz.it.flugzeug.model.Passagier;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * data handler for reading and writing the csv files
 * <p>
 * M133: flughafen
 *
 * @author Kaushican Uthayakumaran
 */

public class DataHandler {
    private static final DataHandler instance = new DataHandler();
    private static Map<String, Flugzeug> flugzeugMap = new HashMap<>();
    private static Map<String, Hersteller> herstellerMap = new HashMap<>();
    private static Map<String, Passagier> passagierMap = new HashMap<>();

    /**
     * default constructor: defeat instantiation
     */
    private DataHandler() {
    }

    /**
     * @return the instance of this class
     */
    public static DataHandler getInstance() {
        return DataHandler.instance;
    }

    /**
     * reads all data from the csv-file into the flugzeugMap
     *
     * @return a map with all books
     */
    public static Map<String,Flugzeug> readFlugzeuge() {

        BufferedReader bufferedReader;
        FileReader fileReader;
        try {
            String flugzeugPath = Config.getProperty("flugzeugFile");
            fileReader = new FileReader(flugzeugPath);
            bufferedReader = new BufferedReader(fileReader);

        } catch (FileNotFoundException fileEx) {
            fileEx.printStackTrace();
            throw new RuntimeException();
        }

        try {
            String line;
            Flugzeug flugzeug = null;
            while ((line = bufferedReader.readLine()) != null) {
                flugzeug = new Flugzeug();
                String[] values = line.split(";");
                flugzeug.setFlugzeugUUID(values[0]);
                flugzeug.setName(values[1]);
                flugzeug.setHoechstgeschwindigkeit(new BigDecimal(values[2]));
                flugzeug.setLaenge(new BigDecimal(values[3]));
                flugzeug.setSpannweite(new BigDecimal(values[4]));
                flugzeug.setAnzPlaetze(new BigDecimal(values[5]));
                Hersteller hersteller = getHerstellerMap().get(values[6]);
                flugzeug.setHersteller(hersteller);


                flugzeugMap.put(values[0], flugzeug);
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
                throw new RuntimeException();
            }
            return flugzeugMap;
        }

    }

    /**
     * reads all data from the csv-file into the passagierMap
     *
     */
    public static void readPassagier() {
        BufferedReader bufferedReader;
        FileReader fileReader;
        try {
            String passagierPath = Config.getProperty("passagierFile");
            fileReader = new FileReader(passagierPath);
            bufferedReader = new BufferedReader(fileReader);

        } catch (FileNotFoundException fileEx) {
            fileEx.printStackTrace();
            throw new RuntimeException();
        }

        try {
            String line;
            Passagier passagier = null;
            while ((line = bufferedReader.readLine()) != null) {
                passagier = new Passagier();
                String[] values = line.split(";");
                passagier.setPassagierUUID(values[0]);
                passagier.setVorname(values[1]);
                passagier.setNachname(values[2]);
                passagier.setGeschlecht(values[3]);
                passagier.setAlter(new BigDecimal(values[4]));
                passagier.setAdresse(values[5]);
                passagier.seteMail(values[6]);
                Flugzeug flugzeug = getFlugzeugMap().get(values[7]);
                passagier.setFlugzeug(flugzeug);



                passagierMap.put(values[0], passagier);
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
                throw new RuntimeException();
            }
        }
    }

    /**
     * writes the herstellerMap to the csv-file
     *
     */
    public static void readHersteller() {
        BufferedReader bufferedReader;
        FileReader fileReader;
        try {
            String herstellerPath = Config.getProperty("herstellerFile");
            fileReader = new FileReader(herstellerPath);
            bufferedReader = new BufferedReader(fileReader);

        } catch (FileNotFoundException fileEx) {
            fileEx.printStackTrace();
            throw new RuntimeException();
        }

        try {
            String line;
            Hersteller hersteller = null;
            while ((line = bufferedReader.readLine()) != null) {
                hersteller = new Hersteller();
                String[] values = line.split(";");
                hersteller.setHerstellerUUID(values[0]);
                hersteller.setName(values[1]);
                hersteller.setStartJahr(new BigDecimal(values[2]));
                hersteller.setUmsatz(new BigDecimal(values[3]));
                hersteller.setAnzModelle(new BigDecimal(values[4]));
                hersteller.setHauptsitz(values[5]);
                hersteller.setAnzMitarbeiter(new BigDecimal(values[6]));
                hersteller.setCeo(values[7]);


                herstellerMap.put(values[0], hersteller);
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
                throw new RuntimeException();
            }
        }
    }



    /**
     * writes the flugzeugMap to the csv-file
     *
     * @param flugzeugMap map with all the flugzeuge
     */
    public static void writeFlugzeuge(Map<String, Flugzeug> flugzeugMap) {
        Writer writer = null;
        FileOutputStream fileOutputStream = null;

        try {
            String flugzeugPath = Config.getProperty("flugzeugFile");
            fileOutputStream = new FileOutputStream(flugzeugPath);
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "utf-8"));

            for (Map.Entry<String, Flugzeug> flugzeugEntry : flugzeugMap.entrySet()) {
                Flugzeug flugzeug = flugzeugEntry.getValue();
                String contents = String.join(";",
                        flugzeug.getFlugzeugUUID(),
                        flugzeug.getName(),
                        flugzeug.getHoechstgeschwindigkeit().toString() ,
                        flugzeug.getLaenge().toString(),
                        flugzeug.getSpannweite().toString(),
                        flugzeug.getAnzPlaetze().toString(),
                        flugzeug.getHersteller().getHerstellerUUID()

                );
                writer.write(contents + '\n');
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            throw new RuntimeException();

        } finally {

            try {
                if (writer != null) {
                    writer.close();
                }

                if (fileOutputStream != null) {
                    fileOutputStream.close();

                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static User readUser(String username, String password) {
        BufferedReader bufferedReader;
        FileReader fileReader;
        try {
            String path = Config.getProperty("userFile");
            fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);

        } catch (FileNotFoundException fileEx) {
            fileEx.printStackTrace();
            throw new RuntimeException();
        }

        User user = new User();
        try {
            String line;

            while ((line = bufferedReader.readLine()) != null &&
                    user.getUserRole().equals("guest")) {

                String[] values = line.split(";");
                if (username.equals(values[0]) &&
                        password.equals(values[1])) {
                    user.setUsername(values[0]);
                    user.setUserRole(values[2]);
                }
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
                throw new RuntimeException();
            }
        }
        return user;
    }


    /**
     * Gets the flugzeugMap
     *
     * @return value of flugzeugMap
     */
    public static Map<String, Flugzeug> getFlugzeugMap() {
        if (flugzeugMap.isEmpty()) {
            readFlugzeuge();
        }
        return flugzeugMap;
    }

    /**
     * Sets the flugzeugMap
     *
     * @param flugzeugMap the value to set
     */

    public static void setFlugzeugMap(Map<String, Flugzeug> flugzeugMap) {
        DataHandler.flugzeugMap = flugzeugMap;
    }


    /**
     * writes the passagierMap to the csv-file
     *
     * @param passagierMap map with all the passagiere
     */
    public static void writePassagiere(Map<String, Passagier> passagierMap) {
        Writer writer = null;
        FileOutputStream fileOutputStream = null;

        try {
            String passagierPath = Config.getProperty("passagierFile");
            fileOutputStream = new FileOutputStream(passagierPath);
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "utf-8"));

            for (Map.Entry<String, Passagier> passagierEntry : passagierMap.entrySet()) {
                Passagier passagier = passagierEntry.getValue();
                String contents = String.join(";",
                        passagier.getPassagierUUID().toString(),
                        passagier.getVorname(),
                        passagier.getNachname(),
                        passagier.getGeschlecht(),
                        passagier.getAlter().toString(),
                        passagier.getAdresse(),
                        passagier.geteMail(),
                        passagier.getFlugzeug().getFlugzeugUUID()

                );
                writer.write(contents + '\n');
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            throw new RuntimeException();

        } finally {

            try {
                if (writer != null) {
                    writer.close();
                }

                if (fileOutputStream != null) {
                    fileOutputStream.close();

                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * writes the herstellerMap to the csv-file
     *
     * @param herstellerMap map with all the passagiere
     */
    public static void writeHersteller(Map<String, Hersteller> herstellerMap) {
        Writer writer = null;
        FileOutputStream fileOutputStream = null;

        try {
            String herstellerPath = Config.getProperty("herstellerFile");
            fileOutputStream = new FileOutputStream(herstellerPath);
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "utf-8"));

            for (Map.Entry<String, Hersteller> herstellerEntry : herstellerMap.entrySet()) {
                Hersteller hersteller = herstellerEntry.getValue();
                String contents = String.join(";",
                        hersteller.getHerstellerUUID(),
                        hersteller.getName(),
                        hersteller.getStartJahr().toString(),
                        hersteller.getUmsatz().toString(),
                        hersteller.getAnzModelle().toString(),
                        hersteller.getHauptsitz(),
                        hersteller.getAnzMitarbeiter().toString(),
                        hersteller.getCeo()

                );
                writer.write(contents + '\n');
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            throw new RuntimeException();

        } finally {

            try {
                if (writer != null) {
                    writer.close();
                }

                if (fileOutputStream != null) {
                    fileOutputStream.close();

                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    /**
     * Gets the passagierMap
     *
     * @return value of passagierMap
     */
    public static Map<String, Passagier> getPassagierMap() {
        if (passagierMap.isEmpty()) {
            readPassagier();
        }
        return passagierMap;
    }


    /**
     * Sets the passagierMap
     *
     * @param passagierMap the value to set
     */

    public static void setPassagierMap(Map<String, Passagier> passagierMap) {
        DataHandler.passagierMap = passagierMap;
    }

    /**
     * Sets the herstellerMap
     *
     * @param herstellerMap the value to set
     */

    public static void setHerstellerMap(Map<String, Hersteller> herstellerMap) {
        DataHandler.herstellerMap = herstellerMap;
    }

    /**
     * Gets the herstellerMap
     *
     * @return value of herstellerMap
     */
    public static Map<String, Hersteller> getHerstellerMap() {
        if (herstellerMap.isEmpty()) {
            readHersteller();
        }
        return herstellerMap;
    }
}
