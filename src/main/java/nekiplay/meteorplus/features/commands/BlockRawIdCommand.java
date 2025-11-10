package nekiplay.meteorplus.features.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import meteordevelopment.meteorclient.commands.Command;
import meteordevelopment.meteorclient.utils.player.ChatUtils;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

public class BlockRawIdCommand extends Command {
	public BlockRawIdCommand() {
		super("rawblockid", "Get raw block id under mouse");
	}
	public void build(LiteralArgumentBuilder<SharedSuggestionProvider> builder) {
		builder.executes(context -> {
			if (mc.hitResult != null && mc.hitResult.getType() == HitResult.Type.BLOCK) {
				BlockPos pos = new BlockPos((int) mc.hitResult.getLocation().x, (int) mc.hitResult.getLocation().y, (int) mc.hitResult.getLocation().z);
				BlockState state = mc.level.getBlockState(pos);
				int raw_id = Block.getId(state);
				info(String.valueOf(raw_id));
			}
			return SINGLE_SUCCESS;
		});
	}
}
