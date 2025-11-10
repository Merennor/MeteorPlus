package nekiplay.main.events.hud;
import nekiplay.main.events.Cancellable;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;

public class RenderExperienceLevelEvent extends Cancellable {
	private static final RenderExperienceLevelEvent INSTANCE = new RenderExperienceLevelEvent();
	private GuiGraphics context;
	private DeltaTracker tickCounter;
	public static RenderExperienceLevelEvent get(GuiGraphics context, DeltaTracker tickCounter) {
		INSTANCE.context = context;
		INSTANCE.tickCounter = tickCounter;
		return INSTANCE;
	}
}
