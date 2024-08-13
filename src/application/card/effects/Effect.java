package application.card.effects;

public class Effect {

	private String name;
	private Target target;
	private Adjustment adjustment;
	private StatType statType;
	private int value;

	public Effect(String name, Target target, Adjustment adjustment, StatType statType, int value) {
		super();
		this.name = name;
		this.target = target;
		this.adjustment = adjustment;
		this.statType = statType;
		this.value = value;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final Target getTarget() {
		return target;
	}

	public final void setTarget(Target target) {
		this.target = target;
	}

	public final Adjustment getAdjustment() {
		return adjustment;
	}

	public final void setAdjustment(Adjustment adjustment) {
		this.adjustment = adjustment;
	}

	public final StatType getStatType() {
		return statType;
	}

	public final void setStatType(StatType statType) {
		this.statType = statType;
	}

	public final int getValue() {
		return value;
	}

	public final void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Effect [name=");
		builder.append(name);
		builder.append(", target=");
		builder.append(target);
		builder.append(", ");
		builder.append(adjustment);
		builder.append(statType);
		builder.append(" by ");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
	
}
