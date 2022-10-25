
pool = require("../utils/db.js");
module.exports = {
    getOrdonnanceModel() { // defines the entity model
        return {
            "id_ordo": 0,
            "date_delivrance": "YYYY/MM/JJ",
            "ville_ordo": "X",
            "description": "X",
            "id_professionneldesante": 0,
            "id_patient": 0
        }
    },

    // GET Ordonnance
    async getAllOrdonnance() {
        try {
            conn = await pool.getConnection()
            sql = "SELECT * FROM Ordonnance ORDER BY id_ordo DESC ;"
            const rows = await conn.query(sql)
            conn.end()
            return rows
        } catch (err) {
            console.log(err)
            throw err
        }
    },

    async getAllOrdonnanceByPatient(email) {
        try {
            conn = await pool.getConnection()
            sql = "SELECT * FROM ordonnance INNER JOIN patients USING (id_patient) WHERE email = ? ORDER BY id_ordo DESC;"
            const rows = await conn.query(sql,email)
            conn.end()
            return rows
        } catch (err) {
            console.log(err)
            throw err
        }
    },
    async getAllOrdonnanceByPatientWithDoc(email) {
        try {
            conn = await pool.getConnection()
            sql = "SELECT ordonnance.*, nom_pro, prenom_pro, patients.email FROM ordonnance INNER JOIN patients USING (id_patient) INNER JOIN professionneldesante USING(id_professionneldesante) WHERE patients.email = ?;"
            const rows = await conn.query(sql, email)
            conn.end()
            return rows
        } catch (err) {
            console.log(err)
            throw err
        }
    },

    async getAllOrdonnanceByPatientBySecu(numSecu) {
        try {
            conn = await pool.getConnection()
            sql = "SELECT * FROM ordonnance INNER JOIN patients USING (id_patient) WHERE numero_sercurite = ? ORDER BY id_ordo DESC;"
            const rows = await conn.query(sql,numSecu)
            conn.end()
            return rows
        } catch (err) {
            console.log(err)
            throw err
        }
    },

    async getAllOrdonnanceByVille(email, Ville) {
        try {
            conn = await pool.getConnection()
            sql = "SELECT ordonnance.*, nom_pro, prenom_pro, patients.email FROM ordonnance INNER JOIN patients USING (id_patient) INNER JOIN professionneldesante USING(id_professionneldesante) WHERE patients.email= ? AND ville_ordo=?  ORDER BY id_ordo DESC;"
            const rows = await conn.query(sql, [email, Ville])
            conn.end()
            return rows
        } catch (err) {
            console.log(err)
            throw err
        }
    },

    async getAllOrdonnanceByNomMedecin(email, nomMedecin) {
        try {
            conn = await pool.getConnection()
            sql = "SELECT ordonnance.*, nom_pro, prenom_pro, patients.email FROM ordonnance INNER JOIN patients USING (id_patient) INNER JOIN professionneldesante USING(id_professionneldesante) WHERE patients.email= ? AND nom_pro = ? ORDER BY id_ordo DESC;"
            const rows = await conn.query(sql, [email, nomMedecin])
            conn.end()
            return rows
        } catch (err) {
            console.log(err)
            throw err
        }
    },

    async getAllOrdonnanceByVilleAndNomMedecin(email, nomMedecin, Ville) {
        try {
            conn = await pool.getConnection()
            sql = "SELECT ordonnance.*, nom_pro, prenom_pro, patients.email FROM ordonnance INNER JOIN patients USING (id_patient) INNER JOIN professionneldesante USING(id_professionneldesante) WHERE patients.email= ? AND nom_pro = ? AND ville_ordo=? ORDER BY id_ordo DESC;"
            const rows = await conn.query(sql, [email, nomMedecin, Ville])
            conn.end()
            return rows
        } catch (err) {
            console.log(err)
            throw err
        }
    },

    async getOneOrdonnance(IdOrdo) {
        try {
            conn = await pool.getConnection()
            sql = "SELECT * FROM Ordonnance WHERE id_ordo=?;"
            const rows = await conn.query(sql, IdOrdo)
            conn.end()
            if (rows.lenght != 0) {
                return rows[0]
            }
            else {
                return [];
            }
        } catch (err) {
            console.log(err)
            throw err
        }
    },

    // ADD Ordonnance
    async addOneOrdoPrescription(dateDelivrance, ville, description, idProfDeSante, idPatient) {
        try {
            conn = await pool.getConnection()
            sql = "INSERT INTO ordonnance (id_ordo, date_delivrance, ville_ordo, description, id_professionneldesante, id_patient) VALUES (NULL, ?,?,?,?,?);"
            const rows = await conn.query(sql, [dateDelivrance, ville, description, idProfDeSante, idPatient])
            conn.end()
            return rows
        } catch (err) {
            console.log(err)
            throw err
        }
    },
    async addOrdoMedimanteuse(dateDelivrance, ville, idProfDeSante, idPatient) {
        try {
            conn = await pool.getConnection()
            sql1 = "INSERT INTO ordonnance (date_delivrance, ville_ordo, description, id_professionneldesante, id_patient) VALUES ( ?,?,NULL,?,?);"
            const rows1 = await conn.query(sql1, [dateDelivrance, ville, idProfDeSante, idPatient])
            sql2 = "SELECT * FROM Ordonnance WHERE id_ordo = (SELECT MAX(id_ordo) FROM Ordonnance);"
            const rows2 = await conn.query(sql2)
            conn.end()
            return rows2[0]
        } catch (err) {
            console.log(err)
            throw err
        }
    },

    // DELETE Ordonnance
    async delOneOrdo(id_ordo) {
        try {
            conn = await pool.getConnection()
            sql1 = "DELETE FROM contenir WHERE id_ordo = ?;"
            const rows1 = await conn.query(sql1, id_ordo)
            sql2 = "DELETE FROM ordonnance WHERE id_ordo = ?;"
            const rows2 = await conn.query(sql2, id_ordo)
            conn.end()
            return rows2
        } catch (err) {
            console.log(err)
            throw err
        }
    },

    // GET Medicament
    async getMedecinAboutOrdonnance(IdMedecin) {
        try {
            conn = await pool.getConnection()
            sql = "SELECT * FROM professionneldesante WHERE id_professionneldesante=?;"
            const rows = await conn.query(sql, IdMedecin)
            conn.end()
            return rows[0]
        } catch (err) {
            console.log(err)
            throw err
        }
    },

    async getListeMedicament(IdOrdo) {
        try {
            conn = await pool.getConnection()
            sql = "SELECT * FROM contenir INNER join listedemedicaments USING(id_medic) where id_ordo = ?;"
            const rows = await conn.query(sql, IdOrdo)
            conn.end()
            return rows
        } catch (err) {
            console.log(err)
            throw err
        }
    },

    async getAllMedicaments() {
        try {
            conn = await pool.getConnection()
            sql = "SELECT * FROM ListeDeMedicaments;"
            const rows = await conn.query(sql)
            conn.end()
            return rows
        } catch (err) {
            console.log(err)
            throw err
        }
    },

    async getMedicamentSansCeuxQuiADeja(IdOrdo) {
        try {
          conn = await pool.getConnection()
          sql = "SELECT * FROM listedemedicaments where id_medic not in (SELECT id_medic FROM contenir WHERE id_ordo = ?);"
          const rows = await conn.query(sql, IdOrdo)
          conn.end()
          return rows
        } catch (err) {
          console.log(err)
          throw err
        }
    },

    //GET Etablissement
    async getEtablissementDuMedecin(IdMedecin) {
        try {
            conn = await pool.getConnection()
            sql = "SELECT * FROM exercer inner join etablissement USING(id_etablissement) where id_professionneldesante = ?;"
            const rows = await conn.query(sql, IdMedecin)
            conn.end()
            return rows
        } catch (err) {
            console.log(err)
            throw err
        }
    },

    // ADD Medicament
    async addOneMedicToOrdo(id_medic, id_ordo, quantité, frequence, duree) {
        try {
            conn = await pool.getConnection()
            sql = "INSERT INTO contenir (id_medic, id_ordo, quantité, frequence, duree, quantiteDonner) VALUES ( ?,?,?,?,?,0);"
            const rows = await conn.query(sql, [id_medic, id_ordo, quantité, frequence, duree])
            conn.end()
            return rows
        } catch (err) {
            console.log(err)
            throw err
        }
    },

    // DELETE Medicament
    async delOneMedicByOrdo(id_ordo, id_medic) {
        try {
            conn = await pool.getConnection()
            sql = "DELETE FROM contenir WHERE id_ordo = ? AND id_medic =?;"
            const rows = await conn.query(sql, [id_ordo,id_medic])
            conn.end()
            return rows
        } catch (err) {
            console.log(err)
            throw err
        }
    },

    // GET Allergies
    async getAllAllergies() {
        try {
            conn = await pool.getConnection()
            sql = "SELECT * FROM allergie;"
            const rows = await conn.query(sql)
            conn.end()
            return rows
        } catch (err) {
            console.log(err)
            throw err
        }
    },
    async getAllAllergiesWithoutPatient(idPatient) {
        try {
            conn = await pool.getConnection()
            sql = "SELECT * FROM allergie left join avoirallergie USING(id_allergie) where id_allergie not in (SELECT id_allergie  FROM allergie left join avoirallergie USING(id_allergie) WHERE id_patient = ?);"
            const rows = await conn.query(sql, idPatient)
            conn.end()
            return rows
        } catch (err) {
            console.log(err)
            throw err
        }
    },
    async getAllAllergiesOfAPatient(idPatient) {
        try {
            conn = await pool.getConnection()
            sql = "SELECT * FROM avoirallergie inner join allergie USING(id_allergie) WHERE id_patient = ?;"
            const rows = await conn.query(sql, idPatient)
            conn.end()
            return rows
         } catch (err) {
            console.log(err)
            throw err
        }
    },

    //UPDATE Allergies
    async updateAllergie(idPatient, idAllergie) {
        try {
            conn = await pool.getConnection()
            sql = "insert into avoirallergie (id_patient, id_allergie) VALUES ( ?, ? );"
            const rows = await conn.query(sql, [idPatient, idAllergie])
            conn.end()
            return rows
        } catch (err) {
            console.log(err)
            throw err
        }
    },

    //DELETE Allergies
    async deleteAllergie(idPatient, idAllergie) {
        try {
            conn = await pool.getConnection()
            sql = " DELETE FROM avoirallergie WHERE avoirallergie.id_patient = ? AND avoirallergie.id_allergie = ?"
            const rows = await conn.query(sql, [idPatient, idAllergie])
            return rows
        } catch (err) {
            console.log(err)
            throw err
        }
    },
    
    // Verification numéro de sécurité sociale existant pour création de l'ordonnance
    async checkNumeroSecurite(NumSecu) {
        try {
            conn = await pool.getConnection()
            sql = "SELECT * FROM patients WHERE numero_sercurite = ?;"
            const rows = await conn.query(sql, NumSecu)
            conn.end()
            if (rows.length == 0){
                return false;
            }
            else{
                return true;
            }
        } catch (err) {
            console.log(err)
            throw err
        }
    }

}