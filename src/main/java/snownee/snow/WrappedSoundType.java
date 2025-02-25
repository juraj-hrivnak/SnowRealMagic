package snownee.snow;

import java.util.Map;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.Maps;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;

public final class WrappedSoundType extends SoundType {

	private final SoundType type;

	private WrappedSoundType(SoundType type) {
		super(type.getVolume(), type.getPitch(), null, null, null, null, null);
		this.type = type;
	}

	@NotNull
	@Override
	public SoundEvent getStepSound() {
		return SoundType.SNOW.getStepSound();
	}

	@NotNull
	@Override
	public SoundEvent getFallSound() {
		return SoundType.SNOW.getFallSound();
	}

	@NotNull
	@Override
	public SoundEvent getBreakSound() {
		return type.getBreakSound();
	}

	@NotNull
	@Override
	public SoundEvent getPlaceSound() {
		return type.getPlaceSound();
	}

	@NotNull
	@Override
	public SoundEvent getHitSound() {
		return type.getHitSound();
	}

	private static final Map<SoundType, SoundType> wrappedSounds = Maps.newConcurrentMap();

	public static SoundType get(SoundType soundType) {
		if (soundType == SoundType.SNOW || soundType instanceof WrappedSoundType) {
			return soundType;
		}
		return wrappedSounds.computeIfAbsent(soundType, WrappedSoundType::new);
	}
}
