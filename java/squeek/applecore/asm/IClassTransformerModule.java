package squeek.applecore.asm;

import net.minecraft.launchwrapper.IClassTransformer;

public interface IClassTransformerModule extends IClassTransformer
{
	String[] getClassesToTransform();
}