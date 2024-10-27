package school.hei.patrimoine.modele.possession;

import static java.util.stream.Collectors.toSet;
import static school.hei.patrimoine.modele.Devise.NON_NOMMEE;
import static school.hei.patrimoine.modele.possession.TypeAgregat.FLUX;

import java.time.LocalDate;
import java.util.Set;
import school.hei.patrimoine.modele.Devise;

public final class GroupePossession extends Possession {

  private final Set<Possession> possessions;

  public GroupePossession(String nom, LocalDate t, Set<Possession> possessions, Devise devise) {
    super(nom, t, possessions.stream().mapToInt(Possession::valeurComptable).sum(), devise);
    this.possessions = possessions;
    typeAgregat(possessions); // sanity check: fails if set is inconsistent
  }

  public GroupePossession(String nom, LocalDate t, Set<Possession> possessions) {
    this(nom, t, possessions, NON_NOMMEE);
  }

  @Override
  public Possession projectionFuture(LocalDate tFutur) {
    return new GroupePossession(
        nom,
        tFutur,
        possessions.stream().map(p -> p.projectionFuture(tFutur)).collect(toSet()),
        devise);
  }

  @Override
  public TypeAgregat typeAgregat() {
    return typeAgregat(possessions);
  }

  private TypeAgregat typeAgregat(Set<Possession> possessions) {
    var typeAgregatSet = possessions.stream().map(Possession::typeAgregat).collect(toSet());
    if (typeAgregatSet.size() > 2) {
      throw new IllegalArgumentException(
          "Les possessions ne peuvent être groupées qu'avec d'autre possessions de même"
              + " typeAgregat, ou de typeAgregat=FLUX. Fixez le groupePossession.nom="
              + nom);
    }
    if (typeAgregatSet.size() == 1) {
      return typeAgregatSet.iterator().next();
    }
    if (typeAgregatSet.size() == 2) {
      typeAgregatSet.remove(FLUX);
      return typeAgregatSet.iterator().next();
    }

    throw new RuntimeException(
        "Imprévu ! Peut-être possessions vide ? groupePossessions.nom="
            + nom
            + ", possessions="
            + possessions);
  }
}
