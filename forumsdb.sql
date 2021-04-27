-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 08, 2020 at 01:45 AM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `forumsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_description` longtext,
  `category_id` varchar(255) DEFAULT NULL,
  `category_image` varchar(255) DEFAULT NULL,
  `category_logo` varchar(255) DEFAULT NULL,
  `category_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_op35ifsyq39mxtmfs1asvbltv` (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `category_description`, `category_id`, `category_image`, `category_logo`, `category_title`) VALUES
(1, 'You can found All topic related to psychology, If you have any problem you can post in one of related topic in this space', 'CAT_1', NULL, NULL, 'Psychology'),
(2, 'You can found All topic related to Education, you can post in one of related topic in this space\n', 'CAT_2', NULL, NULL, 'Education'),
(3, '	\nYou can found All topic related to Health, you can post in one of related topic in this space', 'CAT_3', NULL, NULL, 'Health'),
(4, 'You can found All topic related to Science, you can post in one of related topic in this space\n', 'CAT_4', NULL, NULL, 'Science');

-- --------------------------------------------------------

--
-- Table structure for table `category_topic_list`
--

DROP TABLE IF EXISTS `category_topic_list`;
CREATE TABLE IF NOT EXISTS `category_topic_list` (
  `category_id` bigint(20) NOT NULL,
  `topic_list_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_jwnp48xu71w4y552jqth5oeih` (`topic_list_id`),
  KEY `FKrcwbt00v07ot531aahe7h6hxe` (`category_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category_topic_list`
--

INSERT INTO `category_topic_list` (`category_id`, `topic_list_id`) VALUES
(1, 2),
(1, 1),
(1, 3),
(2, 5),
(2, 4),
(2, 6),
(3, 8),
(3, 7),
(3, 9),
(4, 11),
(4, 10),
(4, 12);

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
CREATE TABLE IF NOT EXISTS `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `is_announcment` bit(1) DEFAULT NULL,
  `post_content` longtext,
  `post_id` varchar(255) DEFAULT NULL,
  `post_satus` int(11) DEFAULT NULL,
  `post_title` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `views` int(11) NOT NULL,
  `post_owner_id` bigint(20) DEFAULT NULL,
  `post_topic_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_28aj2o56lk6nta54yvci3on8b` (`post_id`),
  KEY `FK2fboa0lblwan1trmomj5gayoc` (`post_owner_id`),
  KEY `FKi1a9yar2gcsrvfsbldk6iimnt` (`post_topic_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `created_at`, `is_announcment`, `post_content`, `post_id`, `post_satus`, `post_title`, `updated_at`, `views`, `post_owner_id`, `post_topic_id`) VALUES
(1, '2020-12-08 00:49:40', b'0', '<p class=\"ql-align-center\"><br></p><p class=\"ql-align-center\"><img src=\"https://qph.fs.quoracdn.net/main-qimg-cb6c46ee10bb299090b0fc87fd755c70\"></p><p class=\"ql-align-center\"><br></p><p><span style=\"background-color: initial;\">\"Today, the \'low oil\' light appeared on my way to fetch my kids from school. The car was losing power, so I decided to go the nearest garage (Total Garage in Ramsgate). Once the oil was replaced, I was unable to move the car because the gears weren\'t catching. I explained to the attendants that we were new to the area with no one to assist me with fetching my kids, which had to be done immediately. This amazing petrol attendant, Clement, without any thought, asked me if I could drive a manual. Before I knew it, he parked his car next to me with the keys in the ignition and continued work as normal.</span></p><p><span style=\"background-color: initial;\">I managed to fetch my kids on time. I\'m so thankful he trusted me with his prized possession without knowing my name or driving abilities. When I returned, he allowed me to drop my kids off at home, and for my husband to return with his car. Clement organized a tow truck and someone to fix the car. He was truly my knight in shining armor! I pray God will bless him beyond measure.\"</span></p><p><span style=\"background-color: initial;\">Credit: Laine Herman</span></p>', 'POST_1', 0, 'Life is measured by the steps we take to love others', '2020-12-08 01:31:44', 10, 2, 2),
(2, '2020-12-08 00:52:16', b'0', '<p class=\"ql-align-center\"><br></p><p class=\"ql-align-center\"><img src=\"https://drpsychological.com/wp-content/uploads/2020/12/pexels-rodnae-productions-6003509.jpg\" height=\"853\" width=\"1280\"></p><p class=\"ql-align-center\"><br></p><p class=\"ql-align-center\">Manipulative people have mastered the art of deception. They may appear respectable and sincere, but this is often just a facade; it’s a way to lure you in and trap you in a relationship before they show their true colors.</p><p class=\"ql-align-center\">Manipulative people really aren’t interested in you except as a vehicle for them to take control so you don’t become a reluctant participant in their plans. They have several ways of doing it, as many of you will recognize. They often use what you say and do and wield it in their own ways so that what you have said and done becomes barely recognizable to you. They will try to confuse you, maybe even make you feel you are crazy. They distort the truth and may resort to lying if it serves their end.</p><p class=\"ql-align-center\">Manipulative people can play the victim, making you feel like the person who caused a problem they started but will not take responsibility for. They can be passive, aggressive, or kind and from minute to minute distant, to make you doubt and tackle your fears and insecurities. They often put you on the defensive. They can also be extremely aggressive and vicious, resorting to personal attacks and criticism, stubborn in their quest to get what they want. They intimidate and threaten, and don’t let go until they exhaust you.</p><p class=\"ql-align-center\">Here are nine traits of manipulative people, so you’ll know what to look out for when one comes your way. Understanding these basic working mechanisms can save you from being drawn into a manipulative relationship. Staying alert, staying in touch with what you know to be true about yourself, and expecting what will happen will allow you to avoid conflict and maintain your own integrity.</p><p class=\"ql-align-center\"><strong>Open the next page below to see more</strong></p>', 'POST_2', 0, '9 Things Manipulative People Do', '2020-12-08 00:52:19', 1, 2, 1),
(3, '2020-12-08 00:53:22', b'0', '<p><span style=\"background-color: initial;\">My little brother.</span></p><p><span style=\"background-color: initial;\">He was born almost a decade behind me. We really did not have a lot in common. We would watch his favorites movies and shows together. We would play legos or go outside, but at that point it was a 15 year old and a 6 year old hanging out together. There is only so much you can do. I was a 15 year old selfish teenager, and all I was interested in was girls. He was 6 and idolized his older brother. I still feel the pang of regret when I think of that of it like that. I was young. And stupid.</span></p><p><span style=\"background-color: initial;\">I graduated high school. I was now 18 to my brother’s 9. I left to join the military and everyone came to see me off. What hurt the most? Not my mother crying, nor my girlfriend; I expected that. It was my little brother. He was a 9 year old kid losing his best friend. But I was leaving for the military. I had to be tough, right? I choked up, but I refused to cry.</span></p><p><span style=\"background-color: initial;\">Eventually my brother and I found a few things in common; martial arts, football, rock band, etc. My brother was ALL about Yu-Gi-Oh. He would spend hours making the playing card decks. He would make one for me then we would battle it out.</span></p><p><span style=\"background-color: initial;\">At some later point, I became a “registered” judge for the Yu-Gi-Oh card game. I would come home on leave. We would wake up early on Saturday morning and get ready. We would head down to Shoney’s breakfast bar and pig out on eggs, sausage, waffles, bacon… you name it. We would stuff ourselves until we couldn’t move. Our parents were always strict on diet growing up so this little secret rebellion of ours felt like we were in heaven. Then, after our stomachs settled, we would head over to our local Yu-Gi-Oh card tournament. He would get to play his heart out and I’d be a judge making sure no one cheated. You’d be surprised at how many kids either cheated or just did not know the rules. After the tournament, we would go to the movies, catch an ice cream then finally head home. I wish I spent more time with him, but I always reserved the evenings when I was home for my girlfriend. I eventually married the girl.</span></p>', 'POST_3', 0, 'Who/what has made you cry the hardest and why?', '2020-12-08 01:08:01', 1, 2, 3),
(4, '2020-12-08 01:00:40', b'0', '<p><a href=\"https://www.quora.com/What-did-you-learn-too-late-in-life\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: inherit;\"><strong>What did you learn too late in life?</strong></a></p><ol><li><span style=\"background-color: initial;\">Take your studies seriously.&nbsp;</span><strong style=\"background-color: initial;\">Marks and knowledge do matter.</strong></li><li><span style=\"background-color: initial;\">Don’t get too attached to&nbsp;</span><strong style=\"background-color: initial;\">anyone</strong><span style=\"background-color: initial;\">&nbsp;or&nbsp;</span><strong style=\"background-color: initial;\">anything</strong><span style=\"background-color: initial;\">.</span></li><li><span style=\"background-color: initial;\">Start depending on&nbsp;</span><strong style=\"background-color: initial;\">yourself.</strong><span style=\"background-color: initial;\">&nbsp;You will be the one to stay with you forever.</span></li><li><span style=\"background-color: initial;\">Make your&nbsp;</span><strong style=\"background-color: initial;\">priorities</strong><span style=\"background-color: initial;\">&nbsp;clear.</span></li><li><span style=\"background-color: initial;\">Don’t&nbsp;</span><strong style=\"background-color: initial;\">sacrifice/ compromise</strong><span style=\"background-color: initial;\">&nbsp;with your career for anyone.</span></li><li><span style=\"background-color: initial;\">Start spending time with your&nbsp;</span><strong style=\"background-color: initial;\">parents.</strong></li><li><span style=\"background-color: initial;\">Stop believing others easily.</span><strong style=\"background-color: initial;\">&nbsp;They don’t necessarily tell the truth always.</strong></li><li><span style=\"background-color: initial;\">Don’t be committed to someone just because you&nbsp;</span><strong style=\"background-color: initial;\">invested time on him/her</strong><span style=\"background-color: initial;\">.</span></li><li><span style=\"background-color: initial;\">Help everyone without expecting any returns.&nbsp;</span><strong style=\"background-color: initial;\">Expectation hurts.</strong></li><li><strong style=\"background-color: initial;\">Don’t talk shit about people.</strong><span style=\"background-color: initial;\">&nbsp;Be a little considerate.</span></li><li><span style=\"background-color: initial;\">Stop entertaining</span><strong style=\"background-color: initial;\">&nbsp;fake friends</strong><span style=\"background-color: initial;\">. They are one who gossip about you behind your back.</span></li><li><span style=\"background-color: initial;\">Never discontinue relation with&nbsp;</span><strong style=\"background-color: initial;\">school friends</strong><span style=\"background-color: initial;\">. They are the best.</span></li><li><strong style=\"background-color: initial;\">Don’t follow</strong><span style=\"background-color: initial;\">&nbsp;anything just because your friends are following it.</span></li><li><span style=\"background-color: initial;\">Think about your past&nbsp;</span><strong style=\"background-color: initial;\">mistakes</strong><span style=\"background-color: initial;\">. Scrutinize where you did wrong.&nbsp;</span><strong style=\"background-color: initial;\">Make sure you don’t do it again.</strong></li><li><span style=\"background-color: initial;\">Start spending some time alone.&nbsp;</span><strong style=\"background-color: initial;\">Let your mind speak to you.</strong></li><li><span style=\"background-color: initial;\">If you decide to sit for any</span><strong style=\"background-color: initial;\">&nbsp;competitive exam</strong><span style=\"background-color: initial;\">. Start preparing as early as possible.</span></li><li><strong style=\"background-color: initial;\">Intelligence</strong><span style=\"background-color: initial;\">&nbsp;and&nbsp;</span><strong style=\"background-color: initial;\">marks&nbsp;</strong><span style=\"background-color: initial;\">don’t necessarily correlates.</span></li><li><strong style=\"background-color: initial;\">Speak&nbsp;</strong><span style=\"background-color: initial;\">less.&nbsp;</span><strong style=\"background-color: initial;\">Listen&nbsp;</strong><span style=\"background-color: initial;\">more.</span></li><li><span style=\"background-color: initial;\">Don’t take any&nbsp;</span><strong style=\"background-color: initial;\">insult</strong><span style=\"background-color: initial;\">&nbsp;just because you like talking to that person.</span></li><li><span style=\"background-color: initial;\">Don’t argue with people who are not ready to&nbsp;</span><strong style=\"background-color: initial;\">accept others thought</strong><span style=\"background-color: initial;\">.</span></li><li><span style=\"background-color: initial;\">Don’t take advice from people who aren\'t where you want to be.</span></li><li><span style=\"background-color: initial;\">Don’t take any decision in</span><strong style=\"background-color: initial;\">&nbsp;anger</strong><span style=\"background-color: initial;\">. When you’re angry, you won’t be able to think straight and you tend to use your&nbsp;</span><strong style=\"background-color: initial;\">emotional</strong><span style=\"background-color: initial;\">&nbsp;and&nbsp;</span><strong style=\"background-color: initial;\">illogical&nbsp;</strong><span style=\"background-color: initial;\">side to think.</span></li><li><strong style=\"background-color: initial;\">Start feeding orphan animals</strong><span style=\"background-color: initial;\">. They won’t hurt you.(</span><em style=\"background-color: initial;\">I feed mango leaves quite often)</em></li><li><span style=\"background-color: initial;\">Don’t seek for</span><strong style=\"background-color: initial;\">&nbsp;revenge</strong><span style=\"background-color: initial;\">. Instead wish the best for them so that they can’t harm anyone else.</span></li><li><strong style=\"background-color: initial;\">Stressing&nbsp;</strong><span style=\"background-color: initial;\">about anything you can’t change will only bring wrinkles but no solution.</span></li><li><span style=\"background-color: initial;\">Don’t act&nbsp;</span><strong style=\"background-color: initial;\">overconfident&nbsp;</strong><span style=\"background-color: initial;\">when you don’t know anything. Don’t act&nbsp;</span><strong style=\"background-color: initial;\">under confident&nbsp;</strong><span style=\"background-color: initial;\">when you know things.</span></li><li><span style=\"background-color: initial;\">Trying the same which</span><strong style=\"background-color: initial;\">&nbsp;failed</strong><span style=\"background-color: initial;\">&nbsp;you won’t give any other result.</span></li><li><span style=\"background-color: initial;\">Be&nbsp;</span><strong style=\"background-color: initial;\">curious</strong><span style=\"background-color: initial;\">.</span></li><li><strong style=\"background-color: initial;\">Love yourself the way you are</strong><span style=\"background-color: initial;\">. Don’t listen to anyone. You are beautiful. Always remember.</span></li><li><span style=\"background-color: initial;\">Don’t be afraid of walking&nbsp;</span><strong style=\"background-color: initial;\">alone</strong><span style=\"background-color: initial;\">.</span></li><li><span style=\"background-color: initial;\">It’s okay to seek others&nbsp;</span><strong style=\"background-color: initial;\">help</strong><span style=\"background-color: initial;\">. It won’t hurt your&nbsp;</span><strong style=\"background-color: initial;\">ego</strong><span style=\"background-color: initial;\">.</span></li><li><span style=\"background-color: initial;\">Accept&nbsp;</span><strong style=\"background-color: initial;\">positive&nbsp;</strong><span style=\"background-color: initial;\">people in your life. It’s okay to say no to those who make you feel bad.</span></li><li><span style=\"background-color: initial;\">Always believe in your&nbsp;</span><strong style=\"background-color: initial;\">instincts</strong><span style=\"background-color: initial;\">.</span></li><li><strong style=\"background-color: initial;\">Break yourself&nbsp;</strong><span style=\"background-color: initial;\">from any&nbsp;</span><strong style=\"background-color: initial;\">norms&nbsp;</strong><span style=\"background-color: initial;\">if you don’t want to follow.</span></li><li><span style=\"background-color: initial;\">Never give shit about “</span><strong style=\"background-color: initial;\">what people will say?</strong><span style=\"background-color: initial;\">”. If that also we do, then what they will do. Do what you want. It’s going to become a norm one day.</span></li><li><strong style=\"background-color: initial;\">You get to live the life once. Give it your best shot.</strong></li></ol><p><strong style=\"background-color: initial;\"><em>Here I am not caring about the stares. Welcoming the wind with open arms.</em></strong></p><p class=\"ql-align-center\"><img src=\"https://qph.fs.quoracdn.net/main-qimg-cf34a0b749500883f9e1405ca841297f\"></p>', 'POST_4', 0, 'What did you learn too late in life?', '2020-12-08 01:31:36', 6, 3, 2),
(5, '2020-12-08 01:07:32', b'0', '<p>Like a side-view mirror, sometimes on maps, things look bigger to us than they actually appear. Think about it; a world map shows Greenland as this giant landmass the size of South America when really it’s quite tiny, comparatively speaking.</p><p>Then there is the Mongolian Empire, a massive region conquering of over nine million square miles. But what does that area look like on a map when compared to the rest of the world? Don’t worry; these maps will shed light on the true scope of things and may even help you obtain a more worldly perspective.</p><h2>Greenland Looks Tiny Next To South America</h2><p>If you’re like us, then when you look at a world map, the territory of Greenland seems like it could overtake South America in terms of surface area. Well, we’re here to tell you that is not the case, and this map should put their respective sizes into perspective.</p><p class=\"ql-align-center\"><img src=\"https://www.postfun.com/wp-content/uploads/2020/08/greenland-on-the-equator-photo-u1-20004.jpg?width=800&amp;height=533\" alt=\"Greenland Looks Tiny Next To South America\"></p><p><em>Reddit</em></p><p>Let’s start with Greenland. The island has an area of 2,166,086 sq km, while the continent of South America comes in at a whopping 17,840,000 sq km. That, folks, tells us that South America is a solid 8.2 times larger than the northern territory.</p>', 'POST_5', 0, 'Maps That Show Us A New Perspective', NULL, 0, 3, 1),
(6, '2020-12-08 01:08:34', b'0', '<p><a href=\"https://www.quora.com/q/kbjcpezdbznrzwlz/Psychological-Facts-Psychological-Facts-about-Love-Life-Success-Motivation-and-Depression-and-Anxiety-Diagnosis-Free\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: inherit;\"><strong>Psychological Facts: Psychological Facts about Love ,Life , Success Motivation and Depression and Anxiety Diagnosis Free Resources for Followers.</strong></a></p><p><span style=\"background-color: initial;\">Learning something new about yourself is always interesting and entertaining. And understanding the psychology behind the way we behave, treat others, and express ourselves can be even more appealing. Today, we here at&nbsp;</span><strong style=\"background-color: initial;\">Human Psychology Facts</strong><span style=\"background-color: initial;\">&nbsp;have compiled a list of the most surprising psychology facts that can help you better understand yourself and others. Cheers!</span></p><p><img src=\"https://qph.fs.quoracdn.net/main-qimg-d6455513a02a4d0675cbb4148a68064d\"></p>', 'POST_6', 0, 'Psychological Facts: Psychological Facts about Love ,Life , Success and Motivation', NULL, 0, 3, 3),
(7, '2020-12-08 01:15:02', b'0', '<p><span style=\"background-color: initial;\">I had a puppy with terminal cancer. It was in her lips and made her face swell…but she still liked to go on walks, so I’d walk her around the block.</span></p><p><span style=\"background-color: initial;\">We came across a child - maybe 7 or so, who said, “Your dog is UGLY!”</span></p><p><span style=\"background-color: initial;\">I said nicely…”this puppy is very sick, she has cancer and it makes her face look different than other dogs.”</span></p><p><span style=\"background-color: initial;\">The child repeated “It’s ugly, it’s U.G.L.Y.”(spelling out the word) and started to laugh and point. “Ha ha your dog is ugly!”</span></p><p><span style=\"background-color: initial;\">Okay…this was starting to make me angry…and upset. I saw that the mother was about 6 feet away and barely paying attention.</span></p><p><span style=\"background-color: initial;\">I said to the mother, “Hey, my dog might look a little strange, but can we use this to explain to your son that it’s because she is sick?”</span></p><p><span style=\"background-color: initial;\">She looked at me, shrugged her shoulders, and went back on her phone.</span></p><p><span style=\"background-color: initial;\">The son then kicked his foot toward my dog and said, “She’s ugly, take her away.”</span></p><p><span style=\"background-color: initial;\">He barely missed hitting her - I was livid.</span></p><p><span style=\"background-color: initial;\">I said very solidly and loudly enough for the mother to hear before I left… “You know what’s ugly? Bullies are ugly. Your behavior is ugly. This puppy is dying and she just wants to be happy. I hope you grow up to be a kinder person than you are now. ”</span></p><p><span style=\"background-color: initial;\">My puppy died about 3 months later.</span></p><p><span style=\"background-color: initial;\">Here she was before the swelling got too bad…I thought she was anything but ugly.</span></p><p class=\"ql-align-center\"><img src=\"https://qph.fs.quoracdn.net/main-qimg-cd4bffc1fd4a5be7236dfa6245fc49c3.webp\"></p>', 'POST_7', 0, 'What would you do if a child kicked your dog?', NULL, 0, 4, 3),
(8, '2020-12-08 01:16:17', b'0', '<p><span style=\"background-color: initial;\">Here are some bad morning habits that you should never do in the morning, even if it has been your routine for a long time. These habits will destroy your motivation and lead you down the wrong path for the day, so ditch em!</span></p><p><span style=\"background-color: initial;\">?????</span></p><p><a href=\"https://bit.ly/30UYUMV\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(25, 95, 170); background-color: rgb(255, 255, 255);\"><strong>https://bit.ly/30UYUMV</strong>https://bit.ly/30UYUMV</a></p><p><br></p><p><img src=\"https://qph.fs.quoracdn.net/main-qimg-c9bdf085946230fa7941b6fb9c612f63\"></p>', 'POST_8', 0, '7 Things You Should Never Do in the Morning', NULL, 0, 4, 1),
(9, '2020-12-08 01:17:32', b'0', '<p><strong><img src=\"https://qph.fs.quoracdn.net/main-qimg-0499617e314703ccc3060d617f5b8f05\"></strong></p><p><span style=\"background-color: initial;\">This was me in 3rd grade.</span></p><p><span style=\"background-color: initial;\">When I was in elementary school, there was this kid named Leroy. He was bigger, meaner, and scarier than all the other kids. I was a loner that didn’t have many friends, didn’t talk much, just kept to myself and read my books. Everyone used to bully me because I had braces and glasses and wouldn’t talk. I actually used to get physically beaten up a lot by older kids, to which the adults in the after school program would turn the other cheek and say they couldn’t do anything because they didn’t see it (I was sent home multiple times with bruises, cuts, bleeding, bleeding from my head once even from having my head shoved into a pole) but by far Leroy was the worst. With the after school program, at least that was something I could (and was) taken out of. But Leroy was my age, so we saw a lot of each other, and we had English class together.</span></p><p><span style=\"background-color: initial;\">He was so mean to everyone and just had this air about him that screamed danger. So much hatred and malice and no regret or remorse from a kid only in 3rd/4th grade. He would physically assault me and other kids constantly. It didn’t matter to him that I was the tiny nerd girl less than half his size. I remember there was one time where he actually assaulted me in the classroom while the teacher was there, but there was a bookshelf just tall enough to where she couldn’t see. She was busy and didn’t know what was happening. I was too scared to say anything and all the other classmates that COULD see what was going on didn’t say anything either and it was because we all feared him, even at that age. So he just kept beating me up.</span></p><p><br></p>', 'POST_9', 0, 'Who was the most frightening child you’ve ever met ?', '2020-12-08 01:19:50', 1, 4, 2);

-- --------------------------------------------------------

--
-- Table structure for table `post_replies`
--

DROP TABLE IF EXISTS `post_replies`;
CREATE TABLE IF NOT EXISTS `post_replies` (
  `post_id` bigint(20) NOT NULL,
  `replies_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_cc1206ttsns8nmix7ctgvr7v0` (`replies_id`),
  KEY `FKq5uwch97aronqi560a289kaok` (`post_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `post_replies`
--

INSERT INTO `post_replies` (`post_id`, `replies_id`) VALUES
(1, 1),
(1, 2),
(4, 3),
(9, 4);

-- --------------------------------------------------------

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
CREATE TABLE IF NOT EXISTS `reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `reply_content` longtext,
  `reply_id` varchar(255) DEFAULT NULL,
  `reply_status` int(11) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qvek15hd8qrhixssefnf5b7yx` (`reply_id`),
  KEY `FKnpyg5e6pqr2v1y4y6pacte11q` (`post_id`),
  KEY `FKapyyxlgntertu5okpkr685ir9` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reply`
--

INSERT INTO `reply` (`id`, `created_at`, `reply_content`, `reply_id`, `reply_status`, `updated_at`, `post_id`, `user_id`) VALUES
(1, '2020-12-08 00:54:15', '<p>Hello My name is mahdi im commonting this </p>', 'REPLY_1', 0, NULL, 1, 3),
(2, '2020-12-08 01:13:30', '<ul><li>Hello My name is Hedi ben barka im commenting this post</li></ul><p><br></p>', 'REPLY_2', 0, NULL, 1, 4),
(3, '2020-12-08 01:13:58', '<p>Hello My name is mahdi im commenting this Post</p>', 'REPLY_3', 0, NULL, 4, 4),
(4, '2020-12-08 01:20:03', '<p>Hello I\'m Mahdi I COMMENTED THIS POST</p>', 'REPLY_4', 0, NULL, 9, 3);

-- --------------------------------------------------------

--
-- Table structure for table `report_post`
--

DROP TABLE IF EXISTS `report_post`;
CREATE TABLE IF NOT EXISTS `report_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cause` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `id_post` bigint(20) DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbfc53pohq7ik8bb1xijbdld68` (`id_post`),
  KEY `FKh355t3k7t5evqnrp4c0ps65oh` (`id_user`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `report_post`
--

INSERT INTO `report_post` (`id`, `cause`, `type`, `id_post`, `id_user`) VALUES
(1, '', 3, 1, 5);

-- --------------------------------------------------------

--
-- Table structure for table `report_reply`
--

DROP TABLE IF EXISTS `report_reply`;
CREATE TABLE IF NOT EXISTS `report_reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cause` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `id_reply` bigint(20) DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkadoha60a8878eupta59lsp03` (`id_reply`),
  KEY `FKiswh7a9fyn8pbyldfahhe84kw` (`id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `reviewing_posts`
--

DROP TABLE IF EXISTS `reviewing_posts`;
CREATE TABLE IF NOT EXISTS `reviewing_posts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dislike` bit(1) NOT NULL,
  `liked` bit(1) NOT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmu5u48gjk5t8m8gbsp7734ghr` (`post_id`),
  KEY `FKg85ofaa60a6posdm15pr84xqq` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reviewing_posts`
--

INSERT INTO `reviewing_posts` (`id`, `dislike`, `liked`, `post_id`, `user_id`) VALUES
(1, b'0', b'1', 1, 4),
(2, b'0', b'1', 4, 4),
(3, b'0', b'1', 4, 3),
(4, b'1', b'0', 1, 3),
(5, b'0', b'1', 4, 5);

-- --------------------------------------------------------

--
-- Table structure for table `reviewing_replies`
--

DROP TABLE IF EXISTS `reviewing_replies`;
CREATE TABLE IF NOT EXISTS `reviewing_replies` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dislike` bit(1) NOT NULL,
  `liked` bit(1) NOT NULL,
  `reply_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg90kca98w1jqyxtem304jbgkp` (`reply_id`),
  KEY `FKrvtsf5m8yr8tc0v7gy5lx88io` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reviewing_replies`
--

INSERT INTO `reviewing_replies` (`id`, `dislike`, `liked`, `reply_id`, `user_id`) VALUES
(1, b'0', b'1', 1, 4),
(2, b'0', b'1', 3, 4),
(3, b'1', b'0', 3, 3),
(4, b'0', b'1', 2, 3),
(5, b'0', b'1', 3, 5);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `user_role`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_MODERATOR'),
(3, 'ROLE_MEMEBER');

-- --------------------------------------------------------

--
-- Table structure for table `role_user_list`
--

DROP TABLE IF EXISTS `role_user_list`;
CREATE TABLE IF NOT EXISTS `role_user_list` (
  `role_id` bigint(20) NOT NULL,
  `user_list_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_pjxn7p4nhmot878k6sggwppe2` (`user_list_id`),
  KEY `FK4xunm7tpkqjb5ra9bpahkovca` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
CREATE TABLE IF NOT EXISTS `topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `topic_description` varchar(255) DEFAULT NULL,
  `topic_id` varchar(255) DEFAULT NULL,
  `topic_image` varchar(255) DEFAULT NULL,
  `topic_logo` varchar(255) DEFAULT NULL,
  `topic_title` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bdcyaus9qy4udk049pis37s5d` (`topic_id`),
  KEY `FK8n7r9utm8sjpdfstb4wcqd7qj` (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `topic`
--

INSERT INTO `topic` (`id`, `topic_description`, `topic_id`, `topic_image`, `topic_logo`, `topic_title`, `category_id`) VALUES
(1, 'WHAT PSYCHOLOGY ENTAILS IN LAYMAN\'S TERMS & HOW IT CAN BENEFIT YOUR LIFE TODAY!', 'TOPIC_1', 'Topic_69b3719d-4ffa-4617-a725-35fc50bb5f861.jpg', 'Topic_6bfa66aa-8f72-4a6e-9f45-a119c45210cddownload.jpg', 'UNDERSTANDING PSYCHOLOGY', 1),
(2, 'Supportive and awareness information from mental health professional ', 'TOPIC_2', 'Topic_bc0f2ca7-e279-4eb2-9743-fcc24a4acc721.jpg', 'Topic_f8525eb2-7486-4ae8-b8b4-b0fcfd666bee2.jpg', 'Psychiatric Enlightenment', 1),
(3, 'Human Psychology is the science of mind and human behavior\n', 'TOPIC_3', 'Topic_7448a2c9-7060-433c-b1f6-7b198b5e531a2.jpg', 'Topic_dd6d3377-7fa2-496a-a68d-91a089dc3402download.jpg', 'Human Psychology Facts', 1),
(4, 'Let’s share our experiences of Online Education here in this space.', 'TOPIC_4', 'Topic_7f31a589-d02b-4b23-8710-818080e5fd571.jpg', 'Topic_0a6e3f4b-b0ad-4e35-ba8c-0d189db3f514download.jpg', 'Education From Home', 2),
(7, 'Provide Health Information, Fitness information, Relationship, Beauty and other', 'TOPIC_7', 'Topic_28d10d28-6fe6-4e34-8005-4ed82a4c10b6ap.jpg', 'Topic_ca93c67f-6341-4e22-a198-76f11acb7df9sdd.jpg', 'Everyday Health & Fitness', 3),
(5, 'Things that happen in school the GOOD, the BAD, the UGLY and the BULLY', 'TOPIC_5', 'Topic_d5465daa-9cde-4437-bfd2-53bb9c03a404dfdf.jpg', 'Topic_cdbf657e-9cfc-42da-a2dc-75e449b368d5download.jpg', 'School Experiences', 2),
(6, 'A place for high school teachers to share their knowledge.\n', 'TOPIC_6', 'Topic_60990498-8470-4dea-ac05-13ce15f32c91download.png', 'Topic_deca3d57-59a9-417d-b411-b58a659d5e8edownload.jpg', 'Ask a High School Teacher', 2),
(8, 'A place where you can share what\'s on your mind and your problems.', 'TOPIC_8', 'Topic_c1b730ef-7d0d-45c9-9d58-18456b8f9a5dfg.jpg', 'Topic_375cbdf5-6689-4a12-bb40-56de3a19c9a8download.jpg', 'Express How You Feel', 3),
(9, 'It\'s all about Health and Physical Education', 'TOPIC_9', 'Topic_f7173f80-a223-4f46-8e99-e39c801f5e1dhh.jpg', 'Topic_3ca5ddf5-4a69-4fb3-ac1f-73cbb4b90ac9download.jpg', 'Health is Life!', 3),
(10, 'Engineering is based on physics of mechanics. Let’s share our ideas here.', 'TOPIC_10', 'Topic_b50f63ba-ab26-4122-83ff-5fb328a5008afgfg.png', 'Topic_de0929e5-647a-4bac-9838-6680fc7f5318fgf.png', 'Physics of Engineering', 4),
(11, 'Space to group physics and math lovers from all over the world. Solve your H.W\n', 'TOPIC_11', 'Topic_8ebcd9dd-07be-41d2-b944-acdc19673d5a11.jpg', 'Topic_409adcb3-bb27-4eaa-b34c-5bdd45321558download.jpg', 'Physics and Math', 4),
(12, 'All about Physics and Chemistry\n', 'TOPIC_12', 'Topic_42e82e69-6895-4181-8262-99570a7491a4sds.jpg', 'Topic_2bda861d-5ed9-4893-a41c-03b1973b6ae8download.jpg', 'Physics & Chemistry', 4);

-- --------------------------------------------------------

--
-- Table structure for table `topic_post_list`
--

DROP TABLE IF EXISTS `topic_post_list`;
CREATE TABLE IF NOT EXISTS `topic_post_list` (
  `topic_id` bigint(20) NOT NULL,
  `post_list_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_gh383iouq7166evj8hv0ej1lx` (`post_list_id`),
  KEY `FK3f48oi2igptr4yxu86y667r4t` (`topic_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `topic_post_list`
--

INSERT INTO `topic_post_list` (`topic_id`, `post_list_id`) VALUES
(2, 4),
(1, 5),
(3, 6),
(2, 1),
(1, 2),
(3, 3),
(3, 7),
(1, 8),
(2, 9);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `abou_me` longtext,
  `account_status` int(11) DEFAULT NULL,
  `birth_date` datetime NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `is_anonym` bit(1) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `suspension_end` datetime DEFAULT NULL,
  `suspension_start` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `user_mail` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_pic` varchar(255) DEFAULT NULL,
  `user_role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_a3imlf41l37utmxiquukk8ajc` (`user_id`),
  UNIQUE KEY `UK_1v3df5idmr0miyyukv06c7m59` (`user_mail`),
  UNIQUE KEY `UK_lqjrcobrh9jc8wpcar64q1bfh` (`user_name`),
  KEY `FK4cdd7f450r33fl1uc76qlwih4` (`user_role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `abou_me`, `account_status`, `birth_date`, `created_at`, `first_name`, `is_anonym`, `last_name`, `salt`, `suspension_end`, `suspension_start`, `updated_at`, `user_id`, `user_mail`, `user_name`, `user_password`, `user_pic`, `user_role_id`) VALUES
(1, NULL, 0, '2020-12-08 00:03:05', '2020-12-08 00:03:05', 'adminGenearated', b'0', 'adminGenearated', 'xTcmaqUZ1KPuCXmjv0cf6HNwhZFXAq', NULL, NULL, NULL, 'USER_1', 'admin@forums.com', 'admin', 'YdMdixSRMFAO2bAWwptHcRytSUOCA7volBRxk+j1yco=', 'default.png', 1),
(2, '', 0, '1996-01-30 00:01:00', '2020-12-08 00:45:13', '', b'1', '', 'hXYA4Mpf1XZ93pSKw0MXRpbHT969yL', NULL, NULL, '2020-12-08 00:47:05', 'USER_2', 'boulbeba@yahoo.com', 'Boulbeba', 'cgrPV5iTrhEj/aJA4qDWJ0YBhooP1nTGnUfTQ/1c0iw=', 'User_160738842483207221_2932409403456079_8646340165570134016_o.jpg', 3),
(3, '', 0, '1991-01-21 00:01:00', '2020-12-08 00:45:49', '', b'1', '', '6j3PczKC3k167CKPJVTc691Bh5jFub', NULL, NULL, '2020-12-08 01:18:53', 'USER_3', 'mahdi@gmail.com', 'Mahdi', 'EjjTP++/rkMUkquxJExJBXlFFqBZzDY9T6aFj8jq+vE=', 'User_1607390333User_1607389151sdsd.jpg', 3),
(4, '', 0, '2017-01-21 00:06:00', '2020-12-08 01:10:51', 'Mouhamedhedi', b'0', 'Ben Barka', 'EvQbG60O5i9p9f3HL2uVPJYRh4QuTU', NULL, NULL, '2020-12-08 01:12:59', 'USER_4', 'Benbarka@gmail.com', 'hediBenBarka', '1GWntOUqIRGWpLMOwdjUmYi2Dify6zsXpjWwyFSmj5k=', 'User_1607389979dfdf.png', 3),
(5, '', 0, '2020-01-02 00:12:00', '2020-12-08 01:20:46', '', b'1', '', '60mhK8lX1d54GME6RP23nkFhOrAUCb', NULL, NULL, '2020-12-08 01:31:19', 'USER_5', 'ali@gmail.com', 'alichabchoub', 'q94Z50BMPawVw+zXegaYQX5eO+Y+OxjMGoV3/hn9qqs=', 'User_1607391078Untitled.png', 3);

-- --------------------------------------------------------

--
-- Table structure for table `user_followers`
--

DROP TABLE IF EXISTS `user_followers`;
CREATE TABLE IF NOT EXISTS `user_followers` (
  `user_id` bigint(20) NOT NULL,
  `followers_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_9qtukj0g8c1j5co848plycau9` (`followers_id`),
  KEY `FKokc5w6fibhnthvwnxjxyrlfc1` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_following`
--

DROP TABLE IF EXISTS `user_following`;
CREATE TABLE IF NOT EXISTS `user_following` (
  `user_id` bigint(20) NOT NULL,
  `following_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_dl4gkkii2xo1rgckulf8usuo5` (`following_id`),
  KEY `FKn4mj5gtsm47fikwbu41b6wi9k` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_my_replies`
--

DROP TABLE IF EXISTS `user_my_replies`;
CREATE TABLE IF NOT EXISTS `user_my_replies` (
  `user_id` bigint(20) NOT NULL,
  `my_replies_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_tfley1nmjtwriyn7chwk3qfop` (`my_replies_id`),
  KEY `FKqfeio8k4c28fjer9t0lj5v6rv` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_post_list`
--

DROP TABLE IF EXISTS `user_post_list`;
CREATE TABLE IF NOT EXISTS `user_post_list` (
  `user_id` bigint(20) NOT NULL,
  `post_list_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_ev3c9pw0hmquu1j6wcyntqsox` (`post_list_id`),
  KEY `FKgovacbbj2c1wqmv27279saq9l` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_post_list`
--

INSERT INTO `user_post_list` (`user_id`, `post_list_id`) VALUES
(2, 2),
(2, 1),
(2, 3),
(3, 5),
(3, 4),
(3, 6),
(4, 8),
(4, 7),
(4, 9);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
