package criatoriovirtualapp.jhonata.criatoriovirtual.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

/**
 * Created by Jhonata Galante on 23/02/2018.
 * Contato: jhonata.galante@gmail.com
 */

@Dao
public interface BDao {

    @Query("SELECT * FROM PerfilPassaro")
    List<PerfilPassaro> getAll();


    @Query("SELECT * FROM PerfilPassaro WHERE Nome = :nome LIMIT 1")
    PerfilPassaro findByName(String nome);

    @Query("SELECT * FROM PERFILPASSARO WHERE Numero_da_Anilha = :nanilha LIMIT 1")
    PerfilPassaro findByAnilha(String nanilha);

    @Insert
    void insertAll(List<PerfilPassaro> perfis);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSingle(PerfilPassaro perfil);

    @Update
    void update(PerfilPassaro perfil);

    @Delete
    void delete(PerfilPassaro perfil);
}
