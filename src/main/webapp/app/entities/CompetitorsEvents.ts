import { Events } from './Events';
import { Competitors } from './Competitors';
import { Teams } from './Teams';
import { CompetitorEventStatistics } from './CompetitorEventStatistics';
export interface CompetitorsEvents {
    competitorsEventsId: number;
    competitorId: number;
    eventId: number;
    teamId?: number;
    competitors?: Competitors;
    events?: Events;
    teams?: Teams;
    competitorEventStatistics?: CompetitorEventStatistics[];
}
