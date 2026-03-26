package andrews.pandoras_creatures.entities.bufflon;

/**
 * Immutable description of whether a target should be protected from Bufflon combat AI.
 */
public record BufflonCombatTargetProfile(
        boolean explosiveMob,
        boolean ownersTamedWolf,
        boolean ownersTamedBufflon,
        boolean protectedPlayer,
        boolean tamedHorse,
        boolean tamedCat
) {
}
