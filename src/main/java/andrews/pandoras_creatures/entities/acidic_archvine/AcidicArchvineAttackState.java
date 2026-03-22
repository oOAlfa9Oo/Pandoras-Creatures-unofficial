package andrews.pandoras_creatures.entities.acidic_archvine;

import java.util.Arrays;

public enum AcidicArchvineAttackState {
    IDLE(0, (byte) 4),
    GRABBING(1, (byte) 5),
    CHEWING(2, (byte) 6);

    private final int stateId;
    private final byte entityEventId;

    AcidicArchvineAttackState(int stateId, byte entityEventId) {
        this.stateId = stateId;
        this.entityEventId = entityEventId;
    }

    public int stateId() {
        return stateId;
    }

    public byte entityEventId() {
        return entityEventId;
    }

    public static AcidicArchvineAttackState fromEventId(byte entityEventId) {
        return Arrays.stream(values())
                .filter(state -> state.entityEventId == entityEventId)
                .findFirst()
                .orElse(null);
    }
}
