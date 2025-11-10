package nekiplay.meteorplus.mixin.minecraft.entity;

import meteordevelopment.meteorclient.MeteorClient;
import nekiplay.main.events.PlayerUseMultiplierEvent;
import net.minecraft.client.player.ClientInput;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.Vec2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LocalPlayer.class, priority = 1003)
public abstract class ClientPlayerEntityMixin {
	@Shadow
	public ClientInput input;

	@Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z", ordinal = 0))
	private void hookCustomMultiplier(CallbackInfo ci) {
		final ClientInput input = this.input;


		final PlayerUseMultiplierEvent playerUseMultiplier = new PlayerUseMultiplierEvent(0.2f, 0.2f);
		MeteorClient.EVENT_BUS.post(playerUseMultiplier);
		if (playerUseMultiplier.getForward() == 0.2f && playerUseMultiplier.getSideways() == 0.2f) {
			return;
		}
		Vec2 newMoveVector = calculateNewMoveVector(input.getMoveVector(), playerUseMultiplier);
		// use newMoveVector instead of input.moveVector
	}

	private Vec2 calculateNewMoveVector(Vec2 oldMoveVector, PlayerUseMultiplierEvent playerUseMultiplier) {
		float scaleX = 1.0f / playerUseMultiplier.getForward();
		float scaleY = 1.0f / playerUseMultiplier.getSideways();
		return new Vec2(oldMoveVector.x * scaleX, oldMoveVector.y * scaleY);
	}
}
