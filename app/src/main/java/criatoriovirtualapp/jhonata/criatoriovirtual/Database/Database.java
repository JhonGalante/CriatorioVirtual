package criatoriovirtualapp.jhonata.criatoriovirtual.Database;

import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Jhonata Galante on 23/02/2018.
 * Contato: jhonata.galante@gmail.com
 */


@android.arch.persistence.room.Database(entities = {PerfilPassaro.class}, version = 1)
public abstract class Database extends RoomDatabase{
        public abstract BDao daoAcess();
}
