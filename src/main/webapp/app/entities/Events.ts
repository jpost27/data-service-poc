import { EventAssociations } from './EventAssociations';
import { Venues } from './Venues';
import { EventProviderIds } from './EventProviderIds';
import { EventStatuses } from './EventStatuses';
import { Teams } from './Teams';
import { CompetitorsEvents } from './CompetitorsEvents';
import { Seasons } from './Seasons';
export interface Events {
    eventId: number;
    awayTeamId?: number;
    awayTeamScore?: number;
    homeTeamId?: number;
    homeTeamScore?: number;
    seasonId: number;
    sequence?: number;
    venueId?: number;
    scheduledTime: Date;
    eventStatusCode: string;
    eventTitle?: string;
    seasons?: Seasons;
    teams?: Teams;
    eventStatuses?: EventStatuses;
    venues?: Venues;
    eventProviderIds?: EventProviderIds;
    eventAssociations?: EventAssociations[];
    competitorsEvents?: CompetitorsEvents[];
}
