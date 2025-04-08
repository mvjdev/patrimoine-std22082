package school.hei.patrimoine.cas.pro3;

import school.hei.patrimoine.cas.Cas;
import school.hei.patrimoine.modele.Argent;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;
import school.hei.patrimoine.modele.possession.Possession;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.Set;

import static school.hei.patrimoine.modele.Devise.MGA;

public class BakoCas extends Cas {
    public BakoCas(LocalDate ajd, LocalDate finSimulation, Map<Personne, Double> possesseurs) {
        super(ajd, finSimulation, possesseurs);
    }

    @Override
    protected Devise devise() {
        return MGA;
    }

    @Override
    protected String nom() {
        return "Bako";
    }

    @Override
    protected void init() {

    }

    @Override
    protected void suivi() {

    }

    @Override
    public Set<Possession> possessions() {
        var BNI = new Compte(
                "BNI",
                LocalDate.of(2025, Month.APRIL,8),
                new Argent(2_000_000,MGA)
        );
        var BMOI = new Compte(
                "BMOI",
                LocalDate.of(2025, Month.APRIL,8),
                new Argent(625_000,MGA)
        );
        var Coffre = new Compte(
                "Coffre",
                LocalDate.of(2025, Month.APRIL,8),
                new Argent(1_750_000,MGA)
        );

        var debutTravailBNI = LocalDate.of(2025, Month.APRIL,8) ;
        var finTravailBNI = LocalDate.of(2025, Month.DECEMBER, 31);
        new FluxArgent(
                "Salaire_BNI",
                BNI,
                debutTravailBNI,
                finTravailBNI,
                2,
                Argent.ariary(2_125_000)
        );

        var epargne =  new FluxArgent(
                "ÉPARGNE_BMOI",
                BNI,
                debutTravailBNI,
                finTravailBNI,
                3,
                Argent.ariary(200_000)
        );

        new FluxArgent(
                "Loyer",
                BNI,
                debutTravailBNI,
                finTravailBNI,
                26,
                Argent.ariary(-600_000)
        );

        new FluxArgent(
                "Manger,Transport,...",
                BNI,
                debutTravailBNI,
                finTravailBNI,
                1,
                Argent.ariary(-700_000)
        );

        var ordinateur = new Materiel("Ordinateur",LocalDate.of(2025, Month.APRIL,8),LocalDate.of(2025, Month.APRIL,8), Argent.ariary(3_000_000),-0.12);

        return Set.of(BNI,BMOI,ordinateur,Coffre,epargne);
    }
}
