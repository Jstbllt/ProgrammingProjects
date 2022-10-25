// utils/pharmacie.repository.js

pool = require("../utils/db.js");

module.exports = {

  // GET Pharmacie
  async getOnePharma(email) {
    try {
      conn = await pool.getConnection()
      sql = "SELECT * FROM Pharmacie INNER JOIN utilisateur USING (email) WHERE email = ?;"
      const rows = await conn.query(sql, email)
      conn.end()
      if (rows.length == 1) {
        return rows[0]
      } else {
        return false
      }
    } catch (err) {
      console.log(err)
      throw err
    }
  },

  //UPDATE Pharmacie
  async updatePharmacie(email, nom_pharmacie, numero_telephone) {
    try {
      conn = await pool.getConnection();
      sql = "UPDATE Pharmacie SET nom_pharmacie=?, numero_telephone=? WHERE email=?;";
      const okPacket = await conn.query(sql, [nom_pharmacie, numero_telephone, email]);
      conn.end();
      return okPacket.affectedRows;
    } catch (err) {
      console.log(err)
      throw err;
    }
  },

  //UPDATE Ordonnance
  async updateOrdonnancePh(quantiteDonner, idmedoc, id_ordo) {
    try {
      conn = await pool.getConnection();
      sql = "UPDATE contenir SET quantiteDonner = quantiteDonner + ? WHERE contenir.id_medic = ? AND contenir.id_ordo = ?;";
      const okPacket = await conn.query(sql, [quantiteDonner, idmedoc, id_ordo]);
      conn.end();
      return okPacket.affectedRows;
    } catch (err) {
      throw err;
    }
  },
  async updateOrdonnancePhDesc(id_ordo) {
    try {
      conn = await pool.getConnection();
      sql = "UPDATE ordonnance SET checkDescription = '1' WHERE ordonnance.id_ordo = ?;";
      const okPacket = await conn.query(sql, id_ordo);
      conn.end();
      return okPacket.affectedRows;
    } catch (err) {
      throw err;
    }
  },

  //GET Medicament de l'ordonnance déjà transmis au patient
  async ToutMedocDonner(id_ordo) {
    try {
      conn = await pool.getConnection();
      sql = "SELECT COUNT(*) as nb_medocdonner, (SELECT COUNT(*) from contenir where id_ordo = ?) as nb_ordo FROM contenir where id_ordo = ? and quantité = quantiteDonner;";
      const rows = await conn.query(sql, [id_ordo, id_ordo]);
      conn.end();
      return rows;
    } catch (err) {
      throw err;
    }
  }
}