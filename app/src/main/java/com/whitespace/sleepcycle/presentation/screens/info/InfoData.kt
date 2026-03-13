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
                body = "Every night we go through four to six sleep cycles. Each cycle takes 70-110 minutes to complete (90 on average). On each cycle, we go through three stages - a light stage, a medium stage, and a deep stage. Completing a full cycle allows you to wake up refreshed, rather than groggy mid-cycle."
            ),
            InfoCardModel(
                title = "The Five Stages",
                body = "There are five stages of sleep. Stages 1-4 are categorised as Non-REM sleep. The fifth stage is REM sleep. Scientists categorised these stages based on observed brain activity and physical characteristics during sleep."
            ),
        )
    ),
    InfoSectionModel(
        title = "Non-REM Sleep",
        subtitle = "Stages 1 through 4",
        items = listOf(
            InfoCardModel(
                title = "Stage 1 - Light Sleep",
                body = "The lightest stage of sleep. Brain frequency slows slightly compared to wakefulness. Muscle tone is present in skeletal muscles and breathing remains regular. This stage typically lasts just a few minutes."
            ),
            InfoCardModel(
                title = "Stage 2 - Deeper Sleep",
                body = "Follows Stage 1 and represents a deeper level of sleep. The sleeper becomes harder to awaken. Characterised by saw-tooth waves and sleep spindles visible in EEG readings."
            ),
            InfoCardModel(
                title = "Stages 3 & 4 - Slow Wave Sleep",
                body = "The deepest stages of Non-REM sleep, also called Slow Wave Sleep (SWS) or delta sleep. The EEG shows slow, high-amplitude delta waves. Sleepers in SWS can be extremely difficult to wake sometimes even loud noises over 100 dB won't rouse them. With age, time spent in SWS decreases in favour of Stage 2."
            ),
        )
    ),
    InfoSectionModel(
        title = "REM Sleep",
        subtitle = "Stage 5 - the dream stage",
        items = listOf(
            InfoCardModel(
                title = "Stage 5 - REM Sleep",
                body = "The stage associated with vivid dreaming. Although the EEG resembles wakefulness, skeletal muscles are completely atonic without movement. Breathing becomes erratic, heart rate increases, and eyes move rapidly beneath closed lids. Muscle atonia is thought to have evolved to prevent us from physically acting out our dreams."
            ),
        )
    ),
)