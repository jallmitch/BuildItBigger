package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
    Story humor: http://www.inspire21.com/humor
    Q & A humor: http://www.funology.com/outer-space-jokes/
    Knock Knock: http://www.funology.com/knock-knock-jokes/
 **/

public class Jokes {

    private List<List<String>> knockknock = new ArrayList<>();
    private List<List<String>> questionAnswer = new ArrayList<>();
    private List<List<String>> stories = new ArrayList<>();

    public Jokes(){

        knockknock.add(Arrays.asList("1","knock","Adore","Adore is between us. Open up!"));
        knockknock.add(Arrays.asList("2","knock","Otto","Otto know. I've got amnesia."));
        knockknock.add(Arrays.asList("3","knock","Robin","Robin the piggy bank again."));
        knockknock.add(Arrays.asList("4","knock","Dwayne","Dwayne the bathtub, It's overflowing!"));
        knockknock.add(Arrays.asList("5","knock","Sadie","Sadie magic word and watch me disappear!"));

        questionAnswer.add(Arrays.asList("1","qa","What's a light-year?","The same as a regular year, but with less calories."));
        questionAnswer.add(Arrays.asList("2","qa","What did the alien say to the garden?","Take me to your weeder!"));
        questionAnswer.add(Arrays.asList("3","qa","What is an astronauts favorite key on the keyboard?","The space bar!"));
        questionAnswer.add(Arrays.asList("4","qa","What do you call a loony spaceman?","An astronut."));

        stories.add(
                Arrays.asList("1","story","Bird Humor",
                "\t A young woman named Jessie received a parrot as a gift many years ago. Over time the parrot developed an extremely bad attitude. \n\n" +
                "\t It got to the point that every word out of the bird's mouth was rude and obnoxious. Jessie tried and tried to change the bird's attitude by consistently saying only polite words, playing soft music and anything else she could think of to 'clean up' the bird's attitude and by then 'foul language.'\n\n" +
                "\t Finally, Jessie was fed up and yelled at the parrot. The parrot yelled back. Jessie lost it and shook the parrot. The parrot became uncontrollable and even more rude. Jessie, in desperation, threw up her hands, grabbed the bird and put him in the freezer. For a few minutes the parrot squawked, kicked and screamed.  Then suddenly there was total quiet. Not a peep was heard for over a minute.\n\n" +
                "\t Fearing that she'd hurt the parrot, Jessie quickly opened the door to the freezer.\n\n" +
                "\t The parrot calmly stepped out onto Jessie's outstretched arms and said \"I believe I may have offended you with my rude language and actions. I'm sincerely remorseful for my inappropriate transgressions and I fully intend to do everything I can to correct my rude and unforgivable behavior.\"\n\n" +
                "\t Jessie was stunned at the change in the bird's attitude.  \n\n" +
                "\t As she was about to ask the parrot what had made such a dramatic change in his behavior, the bird spoke-up, very softly, \"May I ask what the turkey did?\""));
        stories.add(
                Arrays.asList("2","story","Cat Humor",
                "\t A cat died and went to Heaven. God met her at the gates and said, \"You have been a good cat all these years. Anything you want is yours for the asking.\"\n\n" +
                "\t The cat thought for a minute and then said, \"All my life I lived on a farm and slept on hard wooden floors. I would like a real fluffy pillow to sleep on.\"\n\n" +
                "\t God said, \"Say no more.\" Instantly the cat had a huge fluffy pillow.\n\n" +
                "\t A few days later, six mice were killed in an accident and they all went to Heaven together. God met the mice at the gates with the same offer that He made to the cat.\n\n" +
                "\t The mice said, \"Well, we have had to run all of our lives: from cats, dogs, and even people with brooms! If we could just have some little roller skates, we would not have to run again.\"\n\n" +
                "\t God answered, \"It is done.\" All the mice had beautiful little roller skates.\n\n" +
                "\t About a week later, God decided to check on the cat. He found her sound asleep on her fluffy pillow. God gently awakened the cat and asked, \"Is everything okay? How have you been doing? Are you happy?\"\n\n" +
                "\t The cat replied, \"Oh, it is WONDERFUL. I have never been so happy in my life. The pillow is so fluffy, and those little 'Meals on Wheels' you have been sending over are delicious!\""));
        stories.add(
                Arrays.asList("3","story","Blonde Humor",
                "\t Jack, a handsome man, walked into a sports bar around 9:58 pm.  He sat down next to this blonde at the bar and stared up at the TV... The 10:00 news was on. The news crew was covering a story of a man on a ledge of a large building preparing to jump.  The blonde looked at Jack and said,  \"Do you think he'll jump?\" Jack says, \"You know what, I bet he will.\"  The blonde replied, \"Well, I bet he won't.\"  Jack placed $30 on the bar and said, \"You're on!\"  \n\n" +
                "\t Just as the blonde placed her money on the bar, the guy did a swan dive off of the building, falling to his death.  The blonde was very upset and handed her $30 to Jack, saying, \"Fair's fair... Here's your money.\"  Jack replied, \"I can't take your money, I saw this earlier on the 5 o'clock news and knew he would jump.  \n\n" +
                "\t \"The blonde replies, \"I did too;  but I didn't think he'd do it again.\"\n" +
                "\t Jack took the money..."));
    }

    public List<String>  getKnockKnock(int number)
    {
        return knockknock.get(number);
    }

    public int getSize(String jokeType)
    {
        switch (jokeType)
        {
            case "qa":
                return questionAnswer.size();
            case "story":
                return stories.size();
            default:
                return knockknock.size();
        }
    }

    public List<String>  getQA(int number)
    {
        return questionAnswer.get(number);
    }

    public List<String> getStory(int number)
    {
        return stories.get(number);
    }
}
