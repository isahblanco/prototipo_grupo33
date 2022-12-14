INSERT INTO Title VALUES 
        (0,	'GROAKER'),
        (1,	'DISHWASHER'),
        (2,	'ROOKIE'),
        (3,	'SCULLION'),
        (4,	'FOOD_NERD'),
        (5,	'AMATEUR'),
        (6,	'KITCHEN_HELPER'),
        (7,	'FOODIE'),
        (8,	'COOK'),
        (9,	'HEAD_OF_TASTES'),
        (10,	'RASSASIER'),
        (11,	'TASTE_MANAGER'),
        (12,	'DEXTEROUS_COOK'),
        (13,	'EARL_OF_EDIBLES'),
        (14,	'DIVA_OF_DELICIOUS'),
        (15,	'RECIPE_CRUPIER'),
        (16,	'CHEF'),
        (17,	'DUKE_OF_DESERTS'),
        (18,	'GOURMET_QUEEN'),
        (19,	'FLAVOUR_WHITCH'),
        (20,	'KNIFE_MASTER'),
        (21,	'FOOD_PHARAOH'),
        (22,	'SOUS_CHEF'),
        (23,	'AROMATIC_LORD'),
        (24,	'TASTE_SORCERER'),
        (25,	'AROMA_GURU'),
        (26,	'APPETIZERS_KING'),
        (27,	'SPICES_SULTAN'),
        (28,	'HEAD_CHEF'),
        (29,	'FRAGRANCE_MAGE'),
        (30,	'TASTE_PROGRAMER'),
        (31,	'MASTER_CHEF'),
        (32,	'FLAVORING_EMPEROR'),
        (33,	'CONDIMENTS_DJIN'),
        (34,	'APPETITE_EMPEROR'),
        (35,	'ROYAL_CHEF'),
        (36,	'FLAVOUR_HACKER'),
        (37,	'GODS_TONGUE');

INSERT INTO Client (password,id,cname,surname,email,exp,clevel,profilePic, biography) VALUES
    ('pedripedri',   'pedri',        'pedro',        'calvo gonzalez',       'calvo@gmail.com',              0,  16,   null, 'Hi, I´m Pedro and I work as a waiter in a restaurant. I´m a big cooking fan, even tho I´m not earnig my life with this yet'),
    ('juanjuan',     'itoo',          'juan',         'ramirez rodriguez',    'ito@gmail.com',                0,  5,   null, 'I´m Juan, I´m not a big fan of cooking... but my wife made me create an account here'),
    ('alvarito',     'alvarito',     'alvaro',       'private private',      'alva@gmail.com',               0,  1,   null, 'HEY'),
    ('FMHulinn',     'FMHulin',      'arturo',       'F M',                  'FM@gmail.com',                 0,  2,   null, ''),
    ('danidani',     'dani',         'Dani',         'Cruz González',        'dani@gmail.com',               30,  20,   null, 'I´m one of the creator/owners of this webPage, I also like food and cooking, but not very complex recipes. Thankyou for using this web page!!'),
    ('useruser',     'user',         'Default',      'User',                 'user.email@gmail.com',         0,  10,   null,    'This user is the default user for developing purposes.'),
    ('chefchef',     'chef',         'Chef',         'Master',               'chef.email@gmail.com',         100, 25,   null,   'This user is a chef which has made a lot of recipes.'),
    ('empty123',     'empty',        '',             '',                     '',                             0,  0,   null,  '');


INSERT INTO Recipe (rname,  preparationTime,    steps,  ingredients, views,  score,  visibility, creator, tags, multimediaFiles) VALUES
    ('Crispy Ground Turkey Tostadas',                                       5, 'Preheat the oven to 375 degrees F (190 degrees C). Place red onion slices and crema in separate small bowls. Zest limes into another small bowl. Halve limes and squeeze juice into a fourth small bowl.++Eat',  '1 red onion, halved and thinly sliced. ¼ cup Mexican crema',
        20,    5,  'true', 'chef',        'breakfast++fitness',             null),
    ('Vegan Cacio e Pepe',                                                  45, 'Add the cashews into a bowl and cover with hot water. Allow cashews to soak for at least 2 hours. When ready to make the sauce, place a pot of water to boil on the stove. Once boiling, add the pasta and cook according to the package instructions.',  '1 lb gluten-free spaghetti (I like Banza). 1 cup raw cashews , soaked at least 2 hours', 
        3,      0,  'true', 'itoo',         'italian++vegan',                null),
    ('Lemon-Lime Ricotta Pound Cake',                                       120, 'Preheat the oven to 350 degrees F (175 degrees C). Grease and flour a 10-inch fluted tube pan (such as Bundt®). Sift flour, baking soda, salt, cardamom, and ginger together in a large bowl. Set aside.',  '3 ⅛ cups all-purpose flour. ½ teaspoon baking soda', 
        9,      3,  'true', 'chef',        'sweet++italian',                null),
    ('Coffee Jelly',                                                        50, 'Dissolve gelatin in the hot water in a small bowl. Pour gelatin mixture, coffee, and sugar in a saucepan and bring to a boil over high heat. Pour coffee mixture into glasses for individual servings or a large pan for cubing. Chill in the refrigerator until solidified, 6 to 7 hours.',  '1 (.25 ounce) package unflavored gelatin. 2 tablespoons hot water', 
        4,      0,  'true', 'FMHulin',     'sweet++breakfast',               null),
    ('Bacon-Wrapped Stuffed Chicken Breasts in the Air Fryer',              20, 'Preheat the air fryer to 350 degrees F (175 degrees C) if recommended by manufacturer. Pat chicken pieces dry with paper towels. Use a sharp knife slice horizontally through the middle, beginning at the thickest part, being careful not to cut all the way through to the other side. Open the 2 sides and spread them out like an open book.',  '3 skinless, boneless chicken breasts. 1 teaspoon lemon-pepper seasoning, or to taste', 
        44440,  7, 'true', 'dani',        'breakfast++fitness',             null),
    ('Mother Earths Baked Beans',                                           30, 'Place bacon in a large skillet and cook over medium-high heat, turning occasionally, until evenly browned, about 10 minutes. Drain bacon slices on paper towels; reserve about 2 tablespoons bacon drippings in the skillet. Crumble bacon and transfer to a slow cooker.',  '1 pound thick-cut bacon. 1 pound ground beef', 
        8,      3,  'true', 'pedri',       'vegan++spanish',                null),
    ('Agua Fresca de Pepino (Cucumber Limeade)',                            75, 'Blend 2 cups water, cucumbers, lime juice, and 2 tablespoons sweetener together in a blender until smooth. Pour into pitcher; add remaining water. Stir in additional sweetener to taste.',  '5 cups water, or to taste. 3 cucumbers, peeled and chopped.',    
        8,      4,  'true', 'chef',        'sweet++spanish',                null),
    ('Turkish Eggs (Cilbir)',                                               15, 'Spoon yogurt into a medium bowl. Grate in garlic and mix to combine. Season with salt, pepper, and cayenne. Add dill and mix thoroughly. Set aside at room temperature.',  '1 cup Greek yogurt, at room temperature. 1 clove garlic', 
         40,      0,  'true', 'chef',       'breakfast++spanish',           null),
    ('Salmon Cake Sliders and Garlic Aioli',                                5, 'Mix salmon, bell pepper, olive oil mayonnaise, bread crumbs, green onion, 1 tablespoon lemon juice, 1 tablespoon cilantro, mustard, hot sauce, Worcestershire sauce, seafood seasoning, garlic powder, salt, and pepper together in a large bowl.',  '1 pound salmon, chopped. ½ cup finely chopped bell pepper', 
        15,     -4, 'true', 'chef',        'japanese++meats',               null),
    ('Slow Cooker Texas Pulled Pork',                                       20, 'Pour the vegetable oil into the bottom of a slow cooker. Place the pork roast into the slow cooker; pour in the barbecue sauce, apple cider vinegar, and chicken broth. Stir in the brown sugar, yellow mustard, Worcestershire sauce, chili powder, onion, garlic, and thyme. Cover and cook on High until the roast shreds easily with a fork, 5 to 6 hours.',  '1 teaspoon vegetable oil. 1 (4 pound) pork shoulder roast.', 
        4511,   5,  'true', 'chef',        'american++meats',               null),
    ('Stove Pot Roast With Mashed Potatoes',                                70, 'Pour the olive oil into a multi-functional pressure cooker (such as an Instant Pot®) and select the Saute function. Cook onion, carrot, celery, and bay leaf until the vegetables are soft and the onion has turned translucent, about 5 minutes.',  '½ tablespoon olive oil,1 cup diced onion', 
        555,   -6,  'true', 'chef',        'american++vegan',               null),
    ('Instant Pot Chicken and Dumplings',                                   50, 'Pour the vegetable oil into the bottom of a slow cooker. Place the pork roast into the slow cooker; pour in the barbecue sauce, apple cider vinegar, and chicken broth. Stir in the brown sugar, yellow mustard, Worcestershire sauce, chili powder, onion, garlic, and thyme. Cover and cook on High until the roast shreds easily with a fork, 5 to 6 hours.',  '1 teaspoon vegetable oil. 1 (4 pound) pork shoulder roast.', 
        8,   2,  'true', 'chef',           'fitness++japanese',             null),
    ('Taco Meat',                                                           20, 'Heat a large skillet over medium-high heat. Cook and stir beef in the hot skillet until browned and crumbly, 5 to 7 minutes.',  '1 pound lean ground beef, ½ teaspoon onion powder', 
        1,   0,  'true', 'chef',           'meats++spanish',                null),
    ('Quick Crispy Parmesan Chicken Breasts',                               7, 'Preheat oven to 400 degrees F (200 degrees C). Line a baking sheet with aluminum foil and spray with cooking spray.',  'cooking spray, ½ cup panko bread crumbs', 
        5,   -5,  'true', 'chef',          'japanese++meats',               null),
    ('Creamy Lemon Chicken Thighs',                                         37, 'Preheat the oven to 400 degrees F (200 degrees C). Place chicken in a large bowl and coat generously with salt, pepper, and paprika.',  '6 chicken thighs, 6 chicken drumsticks', 
        225,   0,  'true', 'chef',          'japanese++meats',              null),
    ('Two-Ingredient Pizza Dough',                                          78, 'Mix flour and Greek yogurt together in a bowl; transfer to a work surface floured with self-rising flour. Knead dough, adding more flour as needed to keep dough from being too sticky, for 8 to 10 minutes.',  '1 ½ cups self-rising flour, plus more for kneading', 
        230,   0,  'true', 'chef',      'italian++american',                 null),
    ('Simple White Cake',                                                   12, 'Preheat oven to 350 degrees F (175 degrees C). Grease and flour a 9x9 inch pan or line a muffin pan with paper liners. ',  '1 cup white sugar, ½ cup butter', 
        0,   0,  'true', 'chef',       'sweet++italian',                    null),
    ('Basic Mashed Potatoes',                                               30, 'Bring a pot of salted water to a boil. Add potatoes and cook until tender but still firm, about 15 minutes; drain.',  '2 pounds baking potatoes, peeled and quartered', 
        10,   0,  'true', 'chef',       'fitness++meat',                    null),
    ('Rich and Simple French Onion Soup',                                   40, 'Melt butter with olive oil in an 8 quart stock pot on medium heat. Add onions and continually stir until tender and translucent. Do not brown the onions.',  '½ cup unsalted butter, 2 tablespoons olive oil', 
        30,   0,  'true', 'chef',        'spanish++vegan',                  null),
    ('The Best Steak Marinade',                                             36, 'Mix olive oil, balsamic vinegar, Worcestershire sauce, soy sauce, Dijon mustard, and garlic in a small bowl. Season with salt and pepper.',  '¼ cup olive oil, ¼ cup balsamic vinegar', 
        4,   0,  'true', 'chef',      'american++meat',                     null),
    ('Easy Mexican Casserole',                                              94, 'Preheat oven to 350 degrees F (175 degrees C). In a large skillet over medium-high heat, cook ground beef until no longer pink. Stir in salsa, reduce heat, and simmer 20 minutes, or until liquid is absorbed. Stir in beans, and heat through.',  '1 pound lean ground beef, 2 cups salsa', 
        5,   0,  'true', 'chef',      'spanish++meat',                      null);

 INSERT INTO BOOK (client,recipe) VALUES
        ('pedri',   5),
        ('pedri',   6),
        ('pedri',   1),
        ('pedri',   4),
        ('pedri',   9),
        ('pedri',   10),
        ('pedri',   3),
        ('pedri',   2),
        ('pedri',   7),
        ('pedri',   14),
        ('pedri',   15),
        ('pedri',   13),
        ('pedri',   19),
        ('itoo',     20),
        ('itoo',     2),
        ('itoo',     9),
        ('itoo',     11),
        ('itoo',     19),
        ('itoo',     18),
        ('itoo',     14),
        ('itoo',     13),
        ('itoo',     12),
        ('itoo',     8),
        ('itoo',     4),
        ('itoo',     7),
        ('dani',    5),
        ('FMHulin', 1),
        ('FMHulin', 5),
        ('FMHulin', 11),
        ('FMHulin', 15),
        ('FMHulin', 14),
        ('FMHulin', 2),
        ('FMHulin', 3),
        ('FMHulin', 9),
        ('FMHulin', 8),
        ('FMHulin', 4),
        ('FMHulin', 12);

INSERT INTO Rate (score,rater,recipe) VALUES
        (1, 'pedri',    1),
        (-1, 'pedri',    2),
        (1, 'pedri',    3),
        (1, 'pedri',    5),
        (-1, 'pedri',    6),
        (-1, 'pedri',    9),
        (1, 'pedri',    10),
        (-1, 'pedri',    11),
        (-1, 'itoo',      9),
        (-1, 'itoo',      11),
        (7, 'dani',     5),
        (1, 'FMHulin',  1),
        (1, 'FMHulin',  3),
        (1, 'FMHulin',  5),
        (1, 'FMHulin',  6),
        (1, 'FMHulin',  7),
        (-1, 'FMHulin',  9),
        (1, 'FMHulin',  10),
        (-1, 'FMHulin',  11),
        (1, 'user',  1),
        (0, 'user',  2),
        (1, 'user',  3),
        (1, 'user',  5),
        (1, 'user',  6),
        (1, 'user',  7),
        (-1, 'user',  9),
        (1, 'user',  10),
        (-1, 'user',  11),
        (1, 'chef',  1),
        (1, 'chef',  2),
        (1, 'chef',  5),
        (1, 'chef',  6),
        (1, 'chef',  7),
        (1, 'chef',  10),
        (-1, 'chef',  11),
        (1, 'empty',  5),
        (1, 'empty',  6),
        (1, 'empty',  7),
        (0, 'empty',  9),
        (1, 'empty',  10),
        (-1, 'empty',  11);
