package school.hei.patrimoine.cas;

import school.hei.patrimoine.cas.pro3.BakoCas;
import school.hei.patrimoine.modele.Personne;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import static school.hei.patrimoine.modele.Argent.ariary;

public class CasSetSupplier implements Supplier<CasSet> {
  @Override
  public CasSet get() {
    BakoCas bakoCas = new BakoCas(
            LocalDate.of(2025, Month.APRIL, 8),
            LocalDate.of(2025, Month.DECEMBER, 31),
            Map.of(new Personne("BakoCas"), 1.));
    return new CasSet(
            Set.of(bakoCas),
            ariary(14_711_657)
    );
  }
}
