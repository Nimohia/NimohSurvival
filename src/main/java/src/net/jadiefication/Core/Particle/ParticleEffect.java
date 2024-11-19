package src.net.jadiefication.Core.Particle;

import org.bukkit.scheduler.BukkitRunnable;

public abstract class ParticleEffect extends BukkitRunnable {
    protected int ticks = 0;
    protected final float duration;

    public ParticleEffect(float duration) {
        this.duration = duration;
    }

    @Override
    public void run() {
        if (ticks >= duration) {
            this.cancel();
            return;
        }
        spawnParticles();
        ticks++;
    }

    protected abstract void spawnParticles();
}
