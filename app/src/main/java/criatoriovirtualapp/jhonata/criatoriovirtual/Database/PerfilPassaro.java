package criatoriovirtualapp.jhonata.criatoriovirtual.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Jhonata Galante on 23/02/2018.
 * Contato: jhonata.galante@gmail.com
 */

@Entity
public class PerfilPassaro {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "Numero_da_Anilha")
    private String nanilha;
    @ColumnInfo(name = "Nome")
    private String nome;
    @ColumnInfo(name = "Data_de_Nascimento")
    private String datanasc;
    @ColumnInfo(name = "Nome_do_Pai")
    private String nomepai;
    @ColumnInfo(name = "Nome_da_Mãe")
    private String nomemae;
    @ColumnInfo(name = "Nome_da_Avó(Pai)")
    private String nomeavofpai;
    @ColumnInfo(name = "Nome_do_Avô(Pai)")
    private String nomeavofmae;
    @ColumnInfo(name = "Nome_da_Avó(Mãe)")
    private String nomeavompai;
    @ColumnInfo(name = "Nome_do_Avô(Mãe)")
    private String nomeavommae;
    @ColumnInfo
    private String nomebisavofpai;
    @ColumnInfo
    private String nomebisavofmae;
    @ColumnInfo
    private String nomebisavompai;
    @ColumnInfo
    private String nomebisavommae;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }

    public String getNanilha() {
        return nanilha;
    }

    public void setNanilha(String nanilha) {
        this.nanilha = nanilha;
    }

    public String getNomepai() {
        return nomepai;
    }

    public void setNomepai(String nomepai) {
        this.nomepai = nomepai;
    }

    public String getNomemae() {
        return nomemae;
    }

    public void setNomemae(String nomemae) {
        this.nomemae = nomemae;
    }

    public String getNomeavofpai() {
        return nomeavofpai;
    }

    public void setNomeavofpai(String nomeavofpai) {
        this.nomeavofpai = nomeavofpai;
    }

    public String getNomeavofmae() {
        return nomeavofmae;
    }

    public void setNomeavofmae(String nomeavofmae) {
        this.nomeavofmae = nomeavofmae;
    }

    public String getNomeavompai() {
        return nomeavompai;
    }

    public void setNomeavompai(String nomeavompai) {
        this.nomeavompai = nomeavompai;
    }

    public String getNomeavommae() {
        return nomeavommae;
    }

    public void setNomeavommae(String nomeavommae) {
        this.nomeavommae = nomeavommae;
    }

    public String getNomebisavofpai() {
        return nomebisavofpai;
    }

    public void setNomebisavofpai(String nomebisavofpai) {
        this.nomebisavofpai = nomebisavofpai;
    }

    public String getNomebisavofmae() {
        return nomebisavofmae;
    }

    public void setNomebisavofmae(String nomebisavofmae) {
        this.nomebisavofmae = nomebisavofmae;
    }

    public String getNomebisavompai() {
        return nomebisavompai;
    }

    public void setNomebisavompai(String nomebisavompai) {
        this.nomebisavompai = nomebisavompai;
    }

    public String getNomebisavommae() {
        return nomebisavommae;
    }

    public void setNomebisavommae(String nomebisavommae) {
        this.nomebisavommae = nomebisavommae;
    }
}
