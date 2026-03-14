package com.whitespace.sleepcycle.presentation.screens.info

import com.whitespace.sleepcycle.domain.InfoCardModel
import com.whitespace.sleepcycle.domain.InfoSectionModel

val infoSections = listOf(
    InfoSectionModel(
        title = "Sleep by Age",
        subtitle = "Recommended hours per night",
        items = listOf(
            InfoCardModel(
                title = "Newborn (0-3 months old)",
                body = "14-17 hours per day.\n\nNewborns have no established day/night rhythm yet. Sleep is spread evenly across the full 24 hours. They should not sleep longer than 4-5 hours at a stretch in the first 5-6 weeks, as their small bodies require frequent feeding. Always place newborns on their back on a firm, flat surface to reduce the risk of SIDS."
            ),
            InfoCardModel(
                title = "Infant (4-11 months old)",
                body = "12-15 hours per day, including naps.\n\nAround 4-6 months, infants begin to distinguish day from night and start settling into a more predictable sleep rhythm. Night sleep lengthens to roughly 6-8 hours. Research shows that night sleep duration is positively linked to the development of communication and problem-solving skills."
            ),
            InfoCardModel(
                title = "Toddler (1-2 years old)",
                body = "11-14 hours per day, including naps.\n\nToddlers typically take one nap per day and sleep longer stretches at night. Short sleep at this age (under 13 hours) has been linked to more emotionally reactive and aggressive behaviour by age 5. Consistent bedtime routines are especially effective at this stage."
            ),
            InfoCardModel(
                title = "Preschool (3-5 years old)",
                body = "10-13 hours per day, including naps.\n\nChildren in this age range gradually drop afternoon naps. Short sleep duration (under 11 hours) at this age has been associated with reduced vocabulary and poorer emotional regulation. Building in quiet time in the afternoon helps even when napping stops."
            ),
            InfoCardModel(
                title = "School-age (6-13 years old)",
                body = "9-11 hours per day.\n\nBy age 6, children should be getting all their sleep at night with no daytime naps. If a school-age child is still napping regularly, it may indicate insufficient night sleep. Adequate sleep supports memory consolidation, attention, and academic performance."
            ),
            InfoCardModel(
                title = "Teen (14-17 years old)",
                body = "8-10 hours per day.\n\nTeens experience a biological shift in their circadian rhythm, making it harder to fall asleep and wake up early. Research shows that teens sleeping 7-8 hours report significantly fewer mental health concerns than those sleeping 6 hours or less."
            ),
            InfoCardModel(
                title = "Young Adult (18-25 years old)",
                body = "7-9 hours per day.\n\nYoung adults are still in a phase of brain maturation. Chronic sleep restriction below 7 hours is associated with impaired cognitive function, weakened immune response, and increased risk of weight gain and mood disorders."
            ),
            InfoCardModel(
                title = "Adult (26-64 years old)",
                body = "7-9 hours per day.\n\nAdults who consistently sleep fewer than 7 hours per night face higher risks of obesity, type 2 diabetes, high blood pressure, and heart disease. Quality matters as much as quantity, fragmented sleep lacks the restorative benefit of consolidated, uninterrupted sleep."
            ),
            InfoCardModel(
                title = "Older Adult (65+ years old)",
                body = "7-8 hours per day.\n\nWith age, time spent in deep slow-wave sleep decreases and sleep becomes lighter and more fragmented. Despite needing slightly less sleep than younger adults, the consequences of poor sleep including increased fall risk, cognitive decline, and reduced immune function remain significant."
            ),
        )
    ),
    InfoSectionModel(
        title = "Sleep Cycles",
        subtitle = "Understanding how sleep works",
        items = listOf(
            InfoCardModel(
                title = "What is a Sleep Cycle?",
                body = "Every night we go through four to six sleep cycles. Each cycle takes 70-110 minutes to complete (90 on average). Each cycle passes through four stages - N1, N2, N3, and REM. Completing a full cycle allows you to wake up refreshed, rather than groggy mid-cycle."
            ),
            InfoCardModel(
                title = "The Four Stages",
                body = "Modern sleep science (AASM, 2007) classifies sleep into four stages. N1, N2, and N3 are Non-REM sleep, and the fourth stage is REM sleep. Each stage plays a distinct role in physical and mental restoration."
            ),
        )
    ),
    InfoSectionModel(
        title = "Non-REM Sleep",
        subtitle = "Stages N1, N2, and N3",
        items = listOf(
            InfoCardModel(
                title = "N1 - Light Sleep",
                body = "The lightest and shortest stage, lasting just 1-5 minutes. Brain activity slows from wakefulness but remains relatively fast. Muscle tone is present and breathing is regular. It is easy to be woken during N1 - many people don't even realise they fell asleep."
            ),
            InfoCardModel(
                title = "N2 - Core Sleep",
                body = "The most dominant stage, making up roughly 45-55% of total sleep time. The sleeper becomes harder to awaken. N2 is characterised by sleep spindles and K-complexes in EEG readings and plays a key role in memory consolidation and motor learning."
            ),
            InfoCardModel(
                title = "N3 - Deep Sleep",
                body = "The deepest stage of Non-REM sleep, also called Slow Wave Sleep (SWS) or delta sleep. The EEG shows slow, high-amplitude delta waves. N3 is critical for physical restoration, immune function, and tissue repair. Sleepers in N3 are very difficult to wake - sometimes even loud noises over 100 dB won't rouse them. With age, time spent in N3 decreases significantly."
            ),
        )
    ),
    InfoSectionModel(
        title = "REM Sleep",
        subtitle = "Stage 4 - the dream stage",
        items = listOf(
            InfoCardModel(
                title = "REM - Rapid Eye Movement Sleep",
                body = "The fourth and final stage of the sleep cycle, associated with vivid dreaming. Although the EEG resembles wakefulness, skeletal muscles are completely atonic without movement. Breathing becomes erratic, heart rate increases, and eyes move rapidly beneath closed lids.\n\nREM sleep is essential for emotional regulation, creativity, and long-term memory formation. The first REM period of the night lasts only about 10 minutes but grows progressively longer in later cycles, sometimes reaching 60 minutes by the final cycle."
            ),
        )
    ),
)