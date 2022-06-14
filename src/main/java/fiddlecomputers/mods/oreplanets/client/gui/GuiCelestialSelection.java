package fiddlecomputers.mods.oreplanets.client.gui;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fiddlecomputers.mods.oreplanets.config.ConfigManagerOP;
import fiddlecomputers.mods.oreplanets.utils.ColorUtils;

@SideOnly(Side.CLIENT)
public class GuiCelestialSelection extends GuiScreen
{
    private GuiButton doneButton;
    private GuiButton azButton;
    private GuiTextField searchField;
    private GuiListCelestialSelection selectionList;
    private String lastFilterText = "";
    private final List<CelestialBody> listCelestial = Lists.newArrayList();

    @Override
    public void initGui()
    {
        this.listCelestial.clear();
        this.listCelestial.addAll(GalaxyRegistry.getRegisteredPlanets().values().stream().filter(planet -> planet.getDimensionID() != 0 && planet.getDimensionID() != ConfigManagerOP.oreplanets_dimension.idDimensionSpaceNether && planet.getReachable() && planet.getTierRequirement() > 0).collect(Collectors.toList()));
        this.listCelestial.addAll(GalaxyRegistry.getRegisteredMoons().values().stream().filter(moon -> moon.getReachable() && moon.getTierRequirement() > 0).collect(Collectors.toList()));
        this.selectionList = new GuiListCelestialSelection(this, this.listCelestial, this.width, this.height, 48, this.height - 32, 36);
        this.doneButton = this.addButton(new GuiButton(0, this.width / 2 - 28, this.height - 26, 100, 20, "gui.done"));
        this.addButton(new GuiButton(1, this.width / 2 + 80, this.height - 26, 100, 20, "gui.cancel"));
        this.doneButton.enabled = false;

        this.addButton(this.azButton = new GuiButton(SortType.A_TO_Z.id, this.width / 2 - 183, 26, 45, 20, "A-Z"));
        this.azButton.enabled = false;
        this.addButton(new GuiButton(SortType.Z_TO_A.id, this.width / 2 - 136, 26, 45, 20, "Z-A"));
        this.addButton(new GuiButton(SortType.TIER.id, this.width / 2 - 89, 26, 45, 20, "Tier"));

        this.searchField = new GuiTextField(0, this.fontRenderer, this.width / 2 - 150, this.height - 23, 100, 14);
        this.searchField.setFocused(true);
        this.searchField.setCanLoseFocus(true);
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();
        this.searchField.updateCursorCounter();

        if (!this.searchField.getText().equals(this.lastFilterText))
        {
            this.lastFilterText = this.searchField.getText();
            this.selectionList.refreshListSearch(this.lastFilterText);
        }
    }

    @Override
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        this.selectionList.handleMouseInput();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        super.keyTyped(typedChar, keyCode);
        this.searchField.textboxKeyTyped(typedChar, keyCode);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
            GuiListCelestialSelectionEntry entry = this.selectionList.getSelectedCelestial();

            if (button.id == 0 && entry != null)
            {
                entry.teleport();
                this.mc.displayGuiScreen(null);
            }
            if (button.id == 1)
            {
                this.mc.displayGuiScreen(null);
            }

            SortType type = SortType.getTypeForButton(button);

            if (type != null)
            {
                for (GuiButton buttonType : this.buttonList)
                {
                    if (SortType.getTypeForButton(buttonType) != null)
                    {
                        buttonType.enabled = true;
                    }
                }
                button.enabled = false;
                this.selectionList.refreshList(type);
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        List<String> infoList = new LinkedList<>();

        if (this.selectionList != null)
        {
            this.selectionList.drawScreen(mouseX, mouseY, partialTicks);
            this.drawCenteredString(this.fontRenderer, "Select Celestial", this.width / 2, 12, 16777215);
            this.drawCenteredString(this.fontRenderer, "fml.menu.mods.search", this.width / 2 - 173, this.height - 21, 16777215);

            for (int i = 0; i < this.selectionList.getSize(); ++i)
            {
                if (this.selectionList.isSelected(i))
                {
                    CelestialBody celestial = this.selectionList.getSelectedCelestial().getCelestialBody();
                    infoList.add(ColorUtils.stringToRGB("149, 200, 237").toColoredFont() + "Basic Information:");

                    try
                    {
                        if (celestial.getDimensionID() != -1)
                        {
                            WorldProvider provider = WorldUtil.getProviderForDimensionClient(celestial.getDimensionID());

                            if (provider instanceof WorldProviderSpace)
                            {
                                WorldProviderSpace space = (WorldProviderSpace)provider;
                                String thermal = "";

                                try
                                {
                                    thermal = String.valueOf(String.format("%.2f", 1.8F * space.getThermalLevelModifier() * 32)) + "\u2103";
                                }
                                catch (Exception e)
                                {
                                    thermal = TextFormatting.RED + "Unknown";
                                }

                                if (space.getDayLength() <= 0)
                                {
                                    infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Day-night Cycle:", "No day-night cycle"));
                                }
                                else
                                {
                                    double dayDouble = space.getDayLength() / 24000;

                                    if (dayDouble % 1 == 0)
                                    {
                                        int dayInt = (int)(space.getDayLength() / 24000);
                                        infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Day-night Cycle:", dayInt + (dayInt == 1 ? " Day" : " Days") + " / " + dayInt * 24 + " hours"));
                                    }
                                    else
                                    {
                                        infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Day-night Cycle:", dayDouble + (dayDouble <= 1 ? " Day" : " Days") + " / " + dayDouble * 24 + " hours"));
                                    }
                                }
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Gravity:", String.valueOf(space.getGravity()) + "g"));
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Wind:", String.valueOf(String.format("%.1f", space.getWindLevel() * 100.0F)) + "%"));
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Thermal:", thermal));
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Breathable Atmosphere:", space.hasBreathableAtmosphere()));
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Corrode Armor:", space.shouldCorrodeArmor()));
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Sound Reduction:", String.valueOf(space.getSoundVolReductionAmount() / 1.0F) + "%"));
                            }
                            if (provider instanceof ISolarLevel)
                            {
                                ISolarLevel solar = (ISolarLevel)provider;
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Solar Level:", String.valueOf(String.format("%.1f", solar.getSolarEnergyMultiplier() * 100.0D)) + "%"));
                            }

                            if (celestial instanceof Planet)
                            {
                                Planet planet = (Planet)celestial;

                                if (!planet.atmosphere.composition.isEmpty())
                                {
                                    infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Gas:", String.valueOf(planet.atmosphere.composition)));
                                }
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Solar System:", planet.getParentSolarSystem().getTranslatedName()));
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Galaxy:", planet.getParentSolarSystem().getTranslatedParentGalaxyName()));

                                if (!GalaxyRegistry.getMoonsForPlanet(planet).isEmpty())
                                {
                                    infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Number of Moons:", String.valueOf(GalaxyRegistry.getMoonsForPlanet(planet).size())));
                                }
                            }
                            else if (celestial instanceof Moon)
                            {
                                Moon moon = (Moon)celestial;

                                if (!moon.atmosphere.composition.isEmpty())
                                {
                                    infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Gas:", String.valueOf(moon.atmosphere.composition)));
                                }
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Solar System:", moon.getParentPlanet().getParentSolarSystem().getTranslatedName()));
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Galaxy:", moon.getParentPlanet().getParentSolarSystem().getTranslatedParentGalaxyName()));
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Parent Planet:", moon.getParentPlanet().getTranslatedName()));
                            }
                        }
                        else
                        {
                            infoList.add(TextFormatting.RED + "No Basic Celestial Info");
                        }
                    }
                    catch (Exception e) {}

                    for (int textSize = 0; textSize < infoList.size(); textSize++)
                    {
                        String text = infoList.get(textSize);
                        int fontHeight = this.fontRenderer.FONT_HEIGHT + 2;
                        int y = 54 + fontHeight * textSize;
                        this.fontRenderer.drawString(text, this.width / 2 - 24, y, 16777215);
                    }
                }
            }
        }
        this.searchField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.selectionList.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        super.mouseReleased(mouseX, mouseY, state);
        this.selectionList.mouseReleased(mouseX, mouseY, state);
        this.searchField.mouseClicked(mouseX, mouseY, state);

        if (state == 1 && mouseX >= this.searchField.x && mouseX < this.searchField.x + this.searchField.width && mouseY >= this.searchField.y && mouseY < this.searchField.y + this.searchField.height)
        {
            this.searchField.setText("");
        }
    }

    public void selectCelestial(@Nullable GuiListCelestialSelectionEntry entry)
    {
        this.doneButton.enabled = entry != null && entry.isReachable();
    }

    private String format(String key, String value)
    {
        return key + " " + TextFormatting.RESET + value;
    }

    private String format(String key, boolean value)
    {
        return key + " " + TextFormatting.RESET + (value ? TextFormatting.GREEN : TextFormatting.RED) + value;
    }

    enum SortType implements Comparator<CelestialBody>
    {
        A_TO_Z(10),
        Z_TO_A(11)
        {
            @Override
            public int compare(CelestialBody celestial1, CelestialBody celestial2)
            {
                return celestial2.getName().compareTo(celestial1.getName());
            }
        },
        TIER(12)
        {
            @Override
            public int compare(CelestialBody celestial1, CelestialBody celestial2)
            {
                return Integer.compare(celestial1.getTierRequirement(), celestial2.getTierRequirement());
            }
        };

        protected static final SortType[] values = SortType.values();
        private int id;

        private SortType(int id)
        {
            this.id = id;
        }

        @Override
        public int compare(CelestialBody celestial1, CelestialBody celestial2)
        {
            return celestial1.getName().compareTo(celestial2.getName());
        }

        @Nullable
        protected static SortType getTypeForButton(GuiButton button)
        {
            for (SortType type : SortType.values)
            {
                if (type.id == button.id)
                {
                    return type;
                }
            }
            return null;
        }
    }
}