package squeek.applecore.client;

import java.text.DecimalFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.util.FoodStats;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import squeek.applecore.api.AppleCoreAccessor;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DebugInfoHandler
{
	private static final DecimalFormat saturationDF = new DecimalFormat("#.##");
	private static final DecimalFormat exhaustionValDF = new DecimalFormat("0.00");
	private static final DecimalFormat exhaustionMaxDF = new DecimalFormat("#.##");

	public static void init()
	{
		MinecraftForge.EVENT_BUS.register(new DebugInfoHandler());
	}

	@SubscribeEvent
	public void onTextRender(RenderGameOverlayEvent.Text textEvent)
	{
		if (textEvent.type != RenderGameOverlayEvent.ElementType.TEXT)
			return;

		Minecraft mc = Minecraft.getMinecraft();
		if (mc.gameSettings.showDebugInfo)
		{
			FoodStats stats = mc.thePlayer.getFoodStats();
			float curExhaustion = AppleCoreAccessor.get().getExhaustion(mc.thePlayer);
			float maxExhaustion = AppleCoreAccessor.get().getMaxExhaustion(mc.thePlayer);
			textEvent.left.add("hunger: " + stats.getFoodLevel() + ", sat: " + saturationDF.format(stats.getSaturationLevel()) + ", exh: " + exhaustionValDF.format(curExhaustion) + "/" + exhaustionMaxDF.format(maxExhaustion));
		}
	}
}
