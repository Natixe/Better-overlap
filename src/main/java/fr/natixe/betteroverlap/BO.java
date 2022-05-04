package fr.natixe.betteroverlap;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(BO.MODID)
public class BO {

  public static final String MODID = "bo";
  // Directly reference a log4j logger.
  private static final Logger LOGGER = LogManager.getLogger();

  public BO() {
    IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

    //ModItems.register(bus);
    //ModBlocks.register(bus);
    bus.addListener(this::setup);
    // Register the enqueueIMC method for modloading
    bus.addListener(this::enqueueIMC);
    // Register the processIMC method for modloading
    bus.addListener(this::processIMC);
    // Register the doClientStuff method for modloading
    bus.addListener(this::doClientStuff);

    // Register ourselves for server and other game events we are interested in
    MinecraftForge.EVENT_BUS.register(this);
  }

  private void setup(final FMLCommonSetupEvent event) {

  }

  private void doClientStuff(final FMLClientSetupEvent event) {

  }

  private void enqueueIMC(final InterModEnqueueEvent event) {
    // some example code to dispatch IMC to another mod
    InterModComms.sendTo("examplemod", "helloworld", () -> {
      LOGGER.info("Hello world from the MDK");
      return "Hello world";
    });
  }

  private void processIMC(final InterModProcessEvent event) {

  }

  @SubscribeEvent
  public void onServerStarting(FMLServerStartingEvent event) {
    // do something when the server starts
    LOGGER.info("HELLO from server starting");
  }


  @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class RegistryEvents {
    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
      // register a new block here
      LOGGER.info("HELLO from Register Block");
    }
  }
}
