import { TeamAssociations } from './TeamAssociations';
import { Events } from './Events';
import { Venues } from './Venues';
import { Competitors } from './Competitors';
import { TeamColors } from './TeamColors';
import { TeamProviderIds } from './TeamProviderIds';
import { CompetitorsEvents } from './CompetitorsEvents';
import { Leagues } from './Leagues';
export interface Teams {
    teamId: number;
    leagueId: number;
    primaryVenueId?: number;
    yearFounded?: number;
    abbreviation?: string;
    market?: string;
    name?: string;
    owner?: string;
    venues?: Venues;
    leagues?: Leagues;
    competitors?: Competitors[];
    events?: Events[];
    competitorsEvents?: CompetitorsEvents[];
    teamColors?: TeamColors[];
    teamProviderIds?: TeamProviderIds;
    teamAssociations?: TeamAssociations[];
}
