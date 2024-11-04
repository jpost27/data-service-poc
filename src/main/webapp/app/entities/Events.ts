import { EventAssociations } from './EventAssociations';
import { Venues } from './Venues';
import { EventProviderIds } from './EventProviderIds';
import { EventStatuses } from './EventStatuses';
import { Teams } from './Teams';
import { CompetitorsEvents } from './CompetitorsEvents';
import { Seasons } from './Seasons';
export interface Events {
    eventId: number;
    scheduledTime: Date;
    eventStatusCode: string;
    seasonId: number;
    awayTeamScore?: number;
    eventTitle?: string;
    homeTeamScore?: number;
    sequence?: number;
    awayTeamId?: number;
    homeTeamId?: number;
    venueId?: number;
    seasons?: Seasons;
    teams?: Teams;
    eventStatuses?: EventStatuses;
    venues?: Venues;
    eventProviderIds?: EventProviderIds;
    eventAssociations?: EventAssociations[];
    competitorsEvents?: CompetitorsEvents[];
}
