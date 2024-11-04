import { Events } from './Events';
import { Competitors } from './Competitors';
import { Teams } from './Teams';
export interface CompetitorsEvents {
    competitorId: number;
    eventId: number;
    teamId: number;
    competitors?: Competitors;
    events?: Events;
    teams?: Teams;
}
