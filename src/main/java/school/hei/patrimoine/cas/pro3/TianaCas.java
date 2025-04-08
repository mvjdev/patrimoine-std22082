package school.hei.patrimoine.cas.pro3;

import school.hei.patrimoine.cas.Cas;
import school.hei.patrimoine.modele.Argent;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.Set;

import static school.hei.patrimoine.modele.Devise.MGA;

public class TianaCas extends Cas {
    public TianaCas(LocalDate ajd, LocalDate finSimulation, Map<Personne, Double> possesseurs) {
        super(ajd, finSimulation, possesseurs);
    }

    @Override
    protected Devise devise() {
        return MGA;
    }

    @Override
    protected String nom() {
        return "Tiana";
    }

    @Override
    protected void init() {

    }

    @Override
    protected void suivi() {

    }

    @Override
    public Set<Possession> possessions() {

        var compte = new Compte(
                "BNI",
                LocalDate.of(2025, Month.APRIL,8),
                new Argent( 60_000_000,MGA)
        );

        var terrain = new Materiel("Terrain",LocalDate.of(2025, Month.APRIL,8),LocalDate.of(2025, Month.APRIL,8), Argent.ariary(100_000_000),0.10);

        var debutTravail = LocalDate.of(2025, Month.APRIL,8) ;
        var finTravail = LocalDate.of(2026, Month.MARCH, 31);
        new FluxArgent(
                "Dépense Mensuelle",
                compte,
                debutTravail,
                finTravail,
                1,
                Argent.ariary(-4_000_000)
        );

        var debutProjet = LocalDate.of(2025, Month.JUNE,1) ;
        var finProjet = LocalDate.of(2025, Month.DECEMBER, 31);

        new FluxArgent(
                "Dépense Mensuelle",
                compte,
                debutProjet,
                finProjet,
                5,
                Argent.ariary(-5_000_000)
        );

        new FluxArgent(
                "Projet - 10% avant lancement",
                compte,
                LocalDate.of(2025, Month.MAY, 1),
                LocalDate.of(2025, Month.MAY, 1),
                12,
                Argent.ariary(7_000_000) // 10% de 70 000 000
        );

        new FluxArgent(
                "Projet - 90% après livraison",
                compte,
                LocalDate.of(2026, Month.JANUARY, 31),
                LocalDate.of(2026, Month.JANUARY, 31),
                12,
                Argent.ariary(63_000_000)
        );

        var pretBancaire = new Dette(
                "Prêt bancaire",
                LocalDate.of(2025, Month.JULY, 27),
                Argent.ariary(-20_000_000)
        );
        var debutRemboursement = LocalDate.of(2025, Month.AUGUST,27) ;
        var finRemboursement = debutRemboursement.plusYears(1);

        new FluxArgent(
                "Remboursement",
                compte,
                debutRemboursement,
                finRemboursement,
                27,
                Argent.ariary(-24_000_000)
        );

        return Set.of(terrain,compte,pretBancaire);
    }
}
