import { TeamAssociations } from './TeamAssociations';
import { Venues } from './Venues';
import { Competitors } from './Competitors';
import { TeamColors } from './TeamColors';
import { TeamProviderIds } from './TeamProviderIds';
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
    teamColors?: TeamColors[];
    teamAssociations?: TeamAssociations[];
    teamProviderIds?: TeamProviderIds;
}
