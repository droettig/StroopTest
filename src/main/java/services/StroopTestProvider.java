package services;

import com.google.inject.Singleton;

import model.StroopTest;

@Singleton
public class StroopTestProvider
{
	private StroopTest activeStroopTest;
	
	public StroopTest getActiveStroopTest()
	{
		return activeStroopTest;
	}

	public void setActiveStroopTest(StroopTest activeStroopTest)
	{
		this.activeStroopTest = activeStroopTest;
	}
	
//	public void createMixedColourWords(ColourType word, )
//	{
//		List<Word> tmp = new ArrayList<Word>();
//
//		for (ColourType colourTypeOfWord : ColourType.values())
//		{
//			Map<ColourType, ColourSpinner> wordMap = wordSpinnerMap.get(colourTypeOfWord);
//
//			for (ColourType colourOfFont : ColourType.values())
//			{
//				for (int k = 0; k < wordMap.get(colourOfFont).getValue(); k++)
//				{
//					tmp.add(WordFactory.createMixedColourWord(colourTypeOfWord, colourOfFont));
//				}
//			}
//		}
//
//		Word[] words = new Word[tmp.size()];
//
//		for (int j = 0; j < tmp.size(); j++)
//		{
//			words[j] = tmp.get(j);
//		}
//	}
}
